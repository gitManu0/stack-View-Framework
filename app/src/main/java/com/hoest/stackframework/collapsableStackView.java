package com.hoest.stackframework;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class collapsableStackView extends FrameLayout {

      private List<collapsableView> collapsableViews = new ArrayList<>();
      private LinearLayout stackContainer;
      private int indexOfTheOneAndOnlyExpandedView = 0;
      private boolean showDone = false;

      private stackViewListener stackListener;

      public collapsableStackView(@NonNull Context context) {
            super(context);

      }

      public collapsableStackView(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

      }

      public collapsableStackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

      }

      public void getUpdatesListener(stackViewListener listener) {
            stackListener = listener;
      }

      public interface stackViewListener {
            void onViewExpanded(View expandedView);

            void onViewCollapsed(View expandedView);

            void onEndReached();

            void collapsedIndex(int index);
      }

      public void addCollapsableViews(List<collapsableView> mCollapsableViewList){
            this.collapsableViews=mCollapsableViewList;
            init();
      }

      private void init() {
            //todo check here if the list is empty or what
            Log.i("initis","addedView:"+collapsableViews.size());
            LayoutInflater.from(getContext()).inflate(R.layout.collapsable_stack_container, this, true);
            stackContainer = findViewById(R.id.stackContainer01);

            collapseAllViewsExcept(0);
            expandView(0);

            setClickListeners(collapsableViews);

            findViewById(R.id.nextButton).setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (indexOfTheOneAndOnlyExpandedView < collapsableViews.size() - 1) {
                              collapseView(indexOfTheOneAndOnlyExpandedView);
                              expandView(indexOfTheOneAndOnlyExpandedView + 1);
                              indexOfTheOneAndOnlyExpandedView++;
                              collapseAllViewsExcept(indexOfTheOneAndOnlyExpandedView);
                        } else {
                              showDone = true;
                              collapseAllViews();
                              findViewById(R.id.nextButton).setVisibility(GONE);
                              stackListener.onEndReached();
                        }
                  }
            });





            findViewById(R.id.doneButton).setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        showDone = false;
                        collapseAllViews();
                        //Add inter face to add listener
                  }
            });
      }

      private void setClickListeners(List<collapsableView> collapsableViews) {
            for (int i = 0; i < collapsableViews.size(); i++) {
                  int finalI = i;
                  collapsableViews.get(i).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                              if(!collapsableViews.get(finalI).isExpanded){
                                    collapseAllViewsExcept(finalI);
                                    expandView(finalI);
                              }
                        }
                  });
            }
      }

      private void collapseAllViewsExcept(int index) {
            for (int i = 0; i < collapsableViews.size(); i++) {
                  if (i != index) {
                        collapseView(i);
                  }
            }
      }

      private void collapseAllViews() {
            for (int i = 0; i < collapsableViews.size(); i++) {
                  collapseView(i);
            }
      }

      private void expandView(int index) {
            collapsableView collapsableView = collapsableViews.get(index);
            collapsableView.expand();

            stackListener.onViewExpanded(collapsableView.getCurrentView());


            View view = collapsableView.getRootView();
            if(view.getParent()==null){
                  ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                          ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.WRAP_CONTENT
                  );
                  stackContainer.removeView(view);
                  stackContainer.addView(view, params);


            }



      }

      private void collapseView(int index) {
            collapsableView collapsableView = collapsableViews.get(index);
            collapsableView.collapse();
            stackListener.collapsedIndex(index);
            stackListener.onViewCollapsed(collapsableView.getCurrentView());
            View view = collapsableView.getRootView();
            stackContainer.removeView(view);
      }
}
