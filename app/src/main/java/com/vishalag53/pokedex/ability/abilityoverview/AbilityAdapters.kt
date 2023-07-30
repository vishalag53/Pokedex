package com.vishalag53.pokedex.ability.abilityoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.databinding.ListAbilityBinding

class AbilityAdapters(
    private var onClickListener: OnClickListener
) : ListAdapter<AbilityEntity,AbilityAdapters.AbilityViewHolder>(DiffCallback){

    class AbilityViewHolder(private var binding: ListAbilityBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(abilityEntity: AbilityEntity){
            binding.abilityEntity = abilityEntity
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<AbilityEntity>(){
        override fun areItemsTheSame(oldItem: AbilityEntity, newItem: AbilityEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AbilityEntity, newItem: AbilityEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder(ListAbilityBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val abilityEntity = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(abilityEntity)
        }
        holder.bind(abilityEntity)
    }

    class OnClickListener(val clickListener : (abilityEntity: AbilityEntity) -> Unit){
        fun onClick(abilityEntity: AbilityEntity) = clickListener(abilityEntity)
    }
}