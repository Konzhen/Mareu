package com.lamzone.maru.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lamzone.maru.R;
import com.lamzone.maru.events.DeleteMeetingEvent;
import com.lamzone.maru.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetings;

    public MeetingAdapter(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    public void setMeetings(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mEmailHolder.setText(meeting.getEmail());
        holder.mInformationHolder.setText(meeting.getStringRoom(meeting.getRoom()) + " - " + meeting.getTime() + " - " + meeting.getSubject());
        Glide.with(holder.mImageHolder.getContext())
                .load(meeting.getUrl(meeting.getRoom()))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mImageHolder);
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.emailHolder)
        TextView mEmailHolder;
        @BindView(R.id.informationHolder)
        TextView mInformationHolder;
        @BindView(R.id.imageHolder)
        ImageView mImageHolder;
        @BindView(R.id.deleteButton)
        ImageButton mDeleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
