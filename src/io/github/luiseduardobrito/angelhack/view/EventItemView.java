package io.github.luiseduardobrito.angelhack.view;

import io.github.luiseduardobrito.angelhack.R;
import io.github.luiseduardobrito.angelhack.model.Event;

import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.widget.LinearLayout;

@EViewGroup(R.layout.event_list_item)
public class EventItemView extends LinearLayout {

	public EventItemView(Context context) {
		super(context);
	}
	
	public void bind(Event event) {
		
	}
}
