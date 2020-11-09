package com.cst.contacts.contacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.details.ContactDetailedActivity
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber

class TagsAdapter(private val tags: List<ContactInfo>): RecyclerView.Adapter<TagsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tagTV: TextView = view.findViewById(R.id.tagTV) as TextView
        val tvDescription: TextView = view.findViewById(R.id.tv_description) as TextView
        val tvImage: TextView = view.findViewById(R.id.tv_image) as TextView

        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                val intent: Intent = Intent(v.context, ContactDetailedActivity::class.java).apply {
                    putExtra("id", tags[position].id)
                }
                v.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name: String = tags[position].name
        val email: List<Email> = tags[position].emails
        val image: String = name.substring(0, 1)
        val phoneNumber: String = tags[position].phoneNumbers[0].number

        holder.tagTV.text = name
        holder.tvDescription.text = phoneNumber
        holder.tvImage.text = image
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}