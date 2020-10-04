package pre.cg.cgandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import pre.cg.cgandroid.R;
import pre.cg.cgandroid.UserActivity;
import pre.cg.cgandroid.pojo.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        final UserViewHolder userViewHolder = new UserViewHolder(view);
        userViewHolder.cardView.setOnClickListener((View v)->{
            int position = userViewHolder.getAdapterPosition();
            User user = userList.get(position);
            Intent intent = new Intent(context, UserActivity.class);
            intent.putExtra(UserActivity.USER_NAME,user.getName());
            intent.putExtra(UserActivity.USER_IMAGE_ID,user.getImageId());
            context.startActivity(intent);
        });
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        Glide.with(context).load(user.getImageId()).into(holder.imageView);
        holder.textView.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public UserViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view;
            this.imageView = view.findViewById(R.id.User_image);
            this.textView = view.findViewById(R.id.User_name);
        }
    }
}
