package com.meli.eduardo.fonseca.pruebaTecnica.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.pruebaTecnica.databinding.ItemProductBinding
import com.meli.eduardo.fonseca.pruebaTecnica.utils.setValue

class ProductAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product, onItemClick)
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onItemClick: ((Product) -> Unit)?) {
            Glide.with(binding.root.context).load(product.image).into(binding.productImage)
            binding.productPrice.setValue(product.price.toString().take(6))
            binding.productDescription.text = product.name
            binding.productInstallments.setValue((product.price / 12.0).toString().take(6))
            binding.productContainer.setOnClickListener {
                onItemClick?.invoke(product)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewItems(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }
}