package kz.alibek.assignment4.ex1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.alibek.assignment4.R
import kz.alibek.assignment4.ex1.model.User

class UserAdapter(private val onUpdateClick: (User) -> Unit, private val onDeleteClick: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHolder>(
    UsersComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser, onUpdateClick, onDeleteClick)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val userEmail: TextView = itemView.findViewById(R.id.user_email)
        private val updateButton: Button = itemView.findViewById(R.id.update_button)
        private val deleteButton: Button = itemView.findViewById(R.id.delete_button)

        fun bind(user: User, onUpdateClick: (User) -> Unit, onDeleteClick: (User) -> Unit) {
            userName.text = user.name
            userEmail.text = user.email
            updateButton.setOnClickListener {
                onUpdateClick(user)
            }
            deleteButton.setOnClickListener {
                onDeleteClick(user)
            }
        }
    }

    class UsersComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}