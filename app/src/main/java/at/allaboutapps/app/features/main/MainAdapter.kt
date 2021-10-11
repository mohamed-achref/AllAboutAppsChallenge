package at.allaboutapps.app.features.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import at.allaboutapps.app.R
import at.allaboutapps.app.databinding.ItemClubBinding
import at.allaboutapps.app.networking.model.Club

class MainAdapter(private var clubs: MutableList<Club>, private var onClubListener: OnClubListener) :
    RecyclerView.Adapter<MainViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        context = parent.context
        val binding = ItemClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, onClubListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(clubs[position], context!!)
    }

    override fun getItemCount(): Int {
        return clubs.size
    }

    // @SuppressLint("NotifyDataSetChanged")
    // fun setClubList(clubs: List<Club>) {
    //     this.clubs = clubs.toMutableList()
    //     notifyDataSetChanged()
    // }

    interface OnClubListener {
        fun onClubClick(position: Int, view: View?)
    }
}

class MainViewHolder(private val binding: ItemClubBinding, onClubListener: MainAdapter.OnClubListener) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    private var onClubListener: MainAdapter.OnClubListener? = onClubListener

    fun onBind(club: Club, context: Context) {
        binding.tvName.text = club.name
        binding.tvCountry.text = club.country
        binding.tvValue.text = context.resources.getString(R.string.clubs_value, club.value)
        Glide.with(itemView.context).load(club.image).into(binding.ivClub)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onClubListener?.onClubClick(adapterPosition, p0!!)
    }
}