package de.jf.flippingblocks.gestures;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import de.jf.flippingblocks.graphics.CentralStyleGenerator;
import de.jf.flippingblocks.graphics.ResizeAnimation;

public class MoveMenu {
	public static void enlargeMenu(LinearLayout layout , Context context) {
		ViewGroup.LayoutParams temp = layout
				.getLayoutParams();

		if (temp.width != CentralStyleGenerator.getMenuWidthExpanded(context)) {
			ResizeAnimation animation = new ResizeAnimation(layout,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthExpanded(context),
					(temp.height));
			layout.startAnimation(animation);
		}
	}

	public static void minimizeMenu(LinearLayout layout, Context context) {
		ViewGroup.LayoutParams temp = layout.getLayoutParams();

		if (temp.width != CentralStyleGenerator.getMenuWidthHidden(context)) {
			ResizeAnimation animation = new ResizeAnimation(layout,
					(temp.width), (temp.height), CentralStyleGenerator.getMenuWidthHidden(context),
					(temp.height));
			layout.startAnimation(animation);
		}

	}
}
