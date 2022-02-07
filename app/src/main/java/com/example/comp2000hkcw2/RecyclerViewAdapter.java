package com.example.comp2000hkcw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp2000hkcw2.model.Project;
import com.squareup.picasso.Picasso;

import java.util.List;

    /* public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context context;
    private List<Project> projectList;
    private Project tvProject;

    public RecyclerViewAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        tvProject = projectList.get(i);
        holder.tvProjectID.setText(tvProject.getProjectID());
        holder.tvStudentID.setText(tvProject.getStudentID());
        holder.tvTitle.setText(tvProject.getTitle());
        holder.tvYear.setText(tvProject.getYear());
        holder.tvFirstName.setText(tvProject.getFirst_Name());
        holder.tvSecondName.setText(tvProject.getSecond_Name());
        Picasso.get().load(tvProject.getPhoto())
                .resize(50,50)
                .into(holder.imgProjPhoto);

        /* String photoBase64Str = tvProject.getPhoto();
        byte[] bytes = Base64.decode(photoBase64Str, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        holder.imgProjPhoto.setImageBitmap(bitmap); */
/*    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
    
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imgProjPhoto;
        private AppCompatTextView tvProjectID, tvStudentID, tvTitle, tvYear, tvFirstName, tvSecondName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProjectID = (AppCompatTextView) itemView.findViewById(R.id.tvLLProjectID);
            tvStudentID = (AppCompatTextView) itemView.findViewById(R.id.tvLLStudentID);
            imgProjPhoto = (AppCompatImageView) itemView.findViewById(R.id.ivProjPhoto);
            tvTitle = (AppCompatTextView) itemView.findViewById(R.id.tvLLTitle);
            tvYear = (AppCompatTextView) itemView.findViewById(R.id.tvLLYear);
            tvFirstName = (AppCompatTextView) itemView.findViewById(R.id.tvLLFirstName);
            tvSecondName = (AppCompatTextView) itemView.findViewById(R.id.tvLLSecondName);
        }
    }
}
 */
