package net.webjoy.jackluo.baseadapter;

/**
 * Created by jackluo on 9/5/15.
 */
public class ItemBean {

    public int ItemImageResId;
    public String ItemTitle;
    public String ItemContent;

    public ItemBean(int itemImageResId,String itemTitle,String itemContent){
        this.ItemImageResId = itemImageResId;
        this.ItemTitle = itemTitle;
        this.ItemContent = itemContent;
    }
}
