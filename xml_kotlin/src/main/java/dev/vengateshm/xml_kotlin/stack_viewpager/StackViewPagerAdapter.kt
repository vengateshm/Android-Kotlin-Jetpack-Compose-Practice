package dev.vengateshm.xml_kotlin.stack_viewpager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.R

class StackViewPagerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<StackViewPagerAdapter.ViewHolder>() {

    private val colors = listOf(
        Color.parseColor("#FF5733"), // Red-Orange
        Color.parseColor("#33FF57"), // Green
        Color.parseColor("#3357FF"), // Blue
        Color.parseColor("#FF33A8"), // Pink
        Color.parseColor("#FFC300"),  // Yellow
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvPageText)
        val cardViewStack: CardView = view.findViewById(R.id.cardViewStack)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stack_pager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.cardViewStack.setCardBackgroundColor(colors[position % colors.size])
    }

    override fun getItemCount(): Int = items.size
}