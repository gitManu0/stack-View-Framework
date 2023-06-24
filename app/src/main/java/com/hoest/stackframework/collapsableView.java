package com.hoest.stackframework;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class collapsableView extends LinearLayout {
      private View expandedView;
      private View collapsedView;

      public boolean isExpanded;

      public collapsableView(Context context) {
            super(context);
            init();
      }

      public collapsableView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
      }

      private void init() {
            setOrientation(VERTICAL);
      }

      public void setViews( View collapsedView,View expandedView) {
            removeAllViews();
            this.expandedView = expandedView;
            this.collapsedView = collapsedView;



            this.expandedView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            this.collapsedView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            addView(this.expandedView);
            addView(this.collapsedView);



      }

      public void expand() {
            removeAllViews();
            addView(expandedView);
            isExpanded=true;
      }

      public void collapse() {
            removeAllViews();
            addView(collapsedView);
            isExpanded=false;
      }





      public View getCurrentView(){
            if(isExpanded){
                  return this.expandedView;
            }else{
                  return this.collapsedView;
            }
      }




}