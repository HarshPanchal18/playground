package com.example.playground.activities.bitspizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R
import kotlinx.android.synthetic.main.card_captioned_image.view.*

class CaptionedImageAdapter(
    private val captions: ArrayList<String>,
    private val imageIds: IntArray,
) : RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // called when the rec-view requires a new view holder
        val cv=
            LayoutInflater.from(parent.context).inflate(R.layout.card_captioned_image,parent,false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // called whenever the recycler view needs to display data in a viewHolder
        /*val cView=holder.cardView
        val drawable =ContextCompat.getDrawable(cView.context,imageIds[position])
        cView.imginfo.setImageDrawable(drawable)

        // populate the data
        cView.contentDescription=captions[position]
        cView.infotext.text=captions[position]*/

        val cardView = holder.cardView
        val imageView: ImageView = cardView.findViewById<View>(R.id.imginfo) as ImageView
        val drawable = ContextCompat.getDrawable(cardView.context, imageIds[position])
        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]
        val textView = cardView.findViewById<View>(R.id.infotext) as TextView
        textView.text = captions[position]
    }

    override fun getItemCount(): Int = captions.size

}
