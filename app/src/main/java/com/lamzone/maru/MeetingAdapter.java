package com.lamzone.maru;

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
import com.lamzone.maru.events.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Meeting;
import model.MeetingContainer;
import model.MeetingList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    public MeetingContainer mMeetings = new MeetingContainer();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetings.meetingList.get(position);
        holder.mEmailHolder.setText(meeting.getEmail());
        holder.mInformationHolder.setText(meeting.getPlace() + " - " + meeting.getTime() + " - " + meeting.getSubject());
        Glide.with(holder.mImageHolder.getContext())
                .load(meeting.getUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mImageHolder);
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.meetingList.size();
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
