package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class QuizResultRecyclerViewAdapter extends RecyclerView.Adapter<QuizResultRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<DogContent> dogList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dogName;
        public TextView dogBreed;
        public TextView dogGender;
        public TextView dogAge;

        public MyViewHolder(View view) {
            super(view);
            dogName = view.findViewById(R.id.dogNameResult);
            dogBreed = view.findViewById(R.id.dogBreedResult);
            dogGender = view.findViewById(R.id.dogGenderResult);
            dogAge = view.findViewById(R.id.dogAgeResult);
        }
    }

    public QuizResultRecyclerViewAdapter(Context context, List<DogContent> dogList){
        this.context = context;
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_quiz_results, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DogContent dog = dogList.get(position);
        holder.dogName.setText(dog.getName());
        holder.dogBreed.setText(dog.getBreed());
        holder.dogGender.setText(dog.getGender());
        holder.dogAge.setText(dog.getAge());
    }

    @Override
    public int getItemCount() {
        return this.dogList.size();
    }


}
