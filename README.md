# Stack-View-Framework
The following project contains 2 classes CollapsableStackView, CollapsableView
The collapsableStackView.java class is the custom View that you need to add to your layout File where you want to add the stack View layout

```
<com.hoest.stackFramework.collapsableStackView
        android:id="@+id/expandableStackView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        />
```

Add Collapsed and Expanded layout files in your `layout/ Collapsed.xml and Expanded.xml` these files here represents 2 state of the view i.e Collapsed and Expanded

in your `MainActivity.java` class add this view by refrencing it from the xml file

``` 
collapsableStackView stackView;
stackView=findViewbyid(R.id.expandableStackView);

```

Create a list of collapsableView.java class and add your states of collapse and expand views in it;

```
List<collapsableView> mCollapsableViewList=new ArrayList<>();
        collapsableView c1=new collapsableView(getContext());
        c1.setViews(LayoutInflater.from(getContext()).inflate(R.layout.collapsed_1, null),
                LayoutInflater.from(getContext()).inflate(R.layout.expanded_1, null));

        collapsableView c2=new collapsableView(getContext());
        c2.setViews(LayoutInflater.from(getContext()).inflate(R.layout.collapsed_2, null),
                LayoutInflater.from(getContext()).inflate(R.layout.expanded_2, null));

        collapsableView c3=new collapsableView(getContext());
        c3.setViews(LayoutInflater.from(getContext()).inflate(R.layout.collapsed_3, null),
                LayoutInflater.from(getContext()).inflate(R.layout.expanded_3, null));

        mCollapsableViewList.add(c1);
        mCollapsableViewList.add(c2);
        mCollapsableViewList.add(c3);

```

Then just simply add the list to your stackView instance

```

stackView.addCollapsableViews(mCollapsableViewList, new collapsableStackView.stackViewListener() {
            @Override
            public void onViewExpanded(View expandedView) {

            }

            @Override
            public void onViewCollapsed(View expandedView) {

            }

            @Override
            public void onEndReached() {

            }

            @Override
            public void collapsedIndex(int index) {

            }
        });

```



