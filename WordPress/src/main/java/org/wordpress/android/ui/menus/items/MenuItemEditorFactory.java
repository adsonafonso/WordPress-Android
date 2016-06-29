package org.wordpress.android.ui.menus.items;

import android.content.Context;
import android.text.TextUtils;

import org.wordpress.android.R;

/**
 * Provides appropriate {@link BaseMenuItemEditor} subclasses.
 */
public class MenuItemEditorFactory {
    public enum ITEM_TYPE {
        NULL,
        POST,
        PAGE,
        CATEGORY,
        TAG,
        LINK,
        JETPACK_TESTIMONIAL,
        JETPACK_PORTFOLIO,
        JETPACK_COMIC,
        CUSTOM;

        public static ITEM_TYPE typeForString(String typeName) {
            if (TextUtils.isEmpty(typeName)) return NULL;

            if (typeName.equalsIgnoreCase(POST.name())) return POST;
            else if (typeName.equalsIgnoreCase(PAGE.name())) return PAGE;
            else if (typeName.equalsIgnoreCase(CATEGORY.name())) return CATEGORY;
            else if (typeName.equalsIgnoreCase(TAG.name())) return TAG;
            else if (typeName.equalsIgnoreCase(LINK.name())) return LINK;
            else if (typeName.equalsIgnoreCase(CUSTOM.name())) return CUSTOM;
            else if (typeName.equalsIgnoreCase(JETPACK_TESTIMONIAL.name())) return JETPACK_TESTIMONIAL;
            else if (typeName.equalsIgnoreCase(JETPACK_PORTFOLIO.name())) return JETPACK_PORTFOLIO;
            else if (typeName.equalsIgnoreCase(JETPACK_COMIC.name())) return JETPACK_COMIC;

            //special case for tag
            // This is a weird behavior of the API and is not documented.
            if (typeName.compareToIgnoreCase("post_tag") == 0) {
                return TAG;
            }
            else if (typeName.compareToIgnoreCase("post_type") == 0) {
                return POST;
            }
            else if (typeName.compareToIgnoreCase("jetpack-testimonial") == 0) {
                return JETPACK_TESTIMONIAL;
            }
            else if (typeName.compareToIgnoreCase("jetpack-portfolio") == 0) {
                return JETPACK_PORTFOLIO;
            }
            else if (typeName.compareToIgnoreCase("jetpack-comic") == 0) {
                return JETPACK_COMIC;
            }

            else return NULL;
        }

        public static ITEM_TYPE typeForIndex(int index){
            if (NULL.ordinal() == index) return NULL;
            if (POST.ordinal() == index) return POST;
            if (PAGE.ordinal() == index) return PAGE;
            if (CATEGORY.ordinal() == index) return CATEGORY;
            if (TAG.ordinal() == index) return TAG;
            if (LINK.ordinal() == index) return LINK;
            if (JETPACK_TESTIMONIAL.ordinal() == index) return JETPACK_TESTIMONIAL;
            if (JETPACK_PORTFOLIO.ordinal() == index) return JETPACK_PORTFOLIO;
            if (JETPACK_COMIC.ordinal() == index) return JETPACK_COMIC;
            if (CUSTOM.ordinal() == index) return CUSTOM;

            return NULL;
        }
    }

    public static int getIconDrawableRes(ITEM_TYPE type) {
        switch (type) {
            case POST:
                return R.drawable.my_site_icon_posts;
            case PAGE:
                return R.drawable.my_site_icon_pages;
            case CATEGORY:
                return R.drawable.stats_icon_categories;
            case TAG:
                return R.drawable.gridicon_tag;
            case LINK:
                return R.drawable.gridicon_link;
            case JETPACK_TESTIMONIAL:
            case JETPACK_PORTFOLIO:
            case JETPACK_COMIC:
                return R.drawable.my_site_icon_posts;
            default:
                return -1;
        }
    }

    public static BaseMenuItemEditor getEditor(Context context, ITEM_TYPE type) {
        switch (type) {
            case PAGE:
                return new PageItemEditor(context);
            case POST:
                return new PostItemEditor(context);
            case CATEGORY:
                return new CategoryItemEditor(context);
            case TAG:
                return new TagItemEditor(context);
            case LINK:
                return new LinkItemEditor(context);
            case JETPACK_TESTIMONIAL:
            case JETPACK_PORTFOLIO:
            case JETPACK_COMIC:
                JetpackCustomItemEditor editor = new JetpackCustomItemEditor(context);
                editor.setJetpackCustomType(type);
                return editor;

            default:
                return null;
        }
    }
}
