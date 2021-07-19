package com.example.openrss.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openrss.Interface.ItemClickListener
import com.example.openrss.Model.Feed
import com.example.openrss.Model.RSSObject
import com.example.openrss.R
import com.squareup.picasso.Picasso

class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener{

    var txtTitle: TextView
    var txtPubDate:TextView
    var txtContent:TextView
    var txtImage: ImageView

    private var itemClickListener: ItemClickListener?=null

    init{
        txtTitle=itemView.findViewById(R.id.txtTitle) as TextView
        txtPubDate=itemView.findViewById(R.id.txtPubDate) as TextView
        txtContent=itemView.findViewById(R.id.txtContent) as TextView
        txtImage=itemView.findViewById(R.id.txtImage) as ImageView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v, adapterPosition, false)
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v, adapterPosition, true)
        return true
    }
}

class FeedAdapter(private val rssObject: RSSObject, private val mContext: Context):RecyclerView.Adapter<FeedViewHolder>(){
    private val inflater:LayoutInflater

    init{
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtPubDate.text = rssObject.items[position].pubDate
        holder.txtContent.text = rssObject.items[position].description
        Picasso.get().load(rssObject.items[position].thumbnail).into(holder.txtImage);

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (!isLongClick) {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(browserIntent)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

}