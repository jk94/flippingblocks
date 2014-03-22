package de.jf.flippingblocks.gestures;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import de.jf.flippingblocks.graphics.GUI_Element_Creator;
import de.jf.flippingblocks.graphics.ResizeAnimation;

public class MoveMenu {
	public static void enlargeMenu(LinearLayout layout) {
		ViewGroup.LayoutParams temp = layout
				.getLayoutParams();

		if (temp.width != GUI_Element_Creator.sideMenuWidthExpanded) {
			ResizeAnimation animation = new ResizeAnimation(layout,
					(temp.width), (temp.height), GUI_Element_Creator.sideMenuWidthExpanded,
					(temp.height));
			layout.startAnimation(animation);
		}
	}

	public static void minimizeMenu(LinearLayout layout) {
		ViewGroup.LayoutParams temp = layout.getLayoutParams();

		if (temp.width != GUI_Element_Creator.sideMenuWidthHidden) {
			ResizeAnimation animation = new ResizeAnimation(layout,
					(temp.width), (temp.height), GUI_Element_Creator.sideMenuWidthHidden,
					(temp.height));
			layout.startAnimation(animation);
		}

	}
}
