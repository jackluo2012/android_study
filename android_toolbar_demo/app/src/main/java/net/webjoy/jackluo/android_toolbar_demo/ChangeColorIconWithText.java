package net.webjoy.jackluo.android_toolbar_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorIconWithText extends View {

    /**
     * 1.attr.xml
     * 2.在布局中使用
     * 3.构造方法中获取自定义属性
     * 4.onMeasure
     * 5.onDraw
     *
     */
    private int mColor = 0xFF45C01A;
    private Bitmap mIconBitmap;
    private String mText = "微信";
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());


    //内存绘图的东西
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    //透明度
    private float mAlpha ;

    private Rect mIconRect; //绘制Icon的范围
    private Rect mTextbound;//绘制Text的范围
    private Paint mTextPaint;//

    public ChangeColorIconWithText(Context context) {
        //super(context);
        this(context,null);
    }

    /**
     * 获取 自定义属性的值
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TypedValue a = context.obtainStyledAttributes(attrs,R.styleable.ChangeColorIconWithText);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChangeColorIconWithText);

        int n = a.getIndexCount();
        for (int i=0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.ChangeColorIconWithText_c_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_c_color:
                    mColor = a.getColor(attr, 0xFF45C01A);
                    break;
                case R.styleable.ChangeColorIconWithText_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorIconWithText_text_size:
                    mTextSize = (int) a.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();//回收


        //绘制字体
        mTextbound = new Rect();
        mTextPaint = new Paint();

        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextbound);
    }

    /**
     * 1.绘制纯色区域
     * 2.设置DST_IN
     * 3.绘制icon
     */
    //icon的边长
    //view的宽度 -leftpadding - rightpadding
    //view的高度 - topPadding -bottomPadding - mTextBound.height

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()-getPaddingRight(),
                getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-mTextbound.height());
        int left = getMeasuredWidth()/2 - iconWidth/2;//横向居中
        //int top = (getMeasuredHeight()-mTextbound.height())/2-iconWidth/2;
        int top = getMeasuredHeight()/2 -(mTextbound.height()+iconWidth)/2;
        mIconRect = new Rect(left,top,left+iconWidth,top+iconWidth);
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
        //内存去你准备mBitmap,setAlpha,绘制纯色,xfermode,图标

        int alpha = (int) Math.ceil(255*mAlpha);//设置透明度的范围

        setupTargetBitmap(alpha);

        //1.绘制原文本,2,绘制变色的文本
        drawSourceText(canvas,alpha);

        drawTargetText(canvas,alpha);

        canvas.drawBitmap(mBitmap,0,0,null);
    }

    /**
     * 绘制变色的文本
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        //draw text
        int x = mIconRect.left+mIconRect.width()/2-mTextbound.width()/2;
        int y = mIconRect.bottom + mTextbound.height();

        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 绘制原文本
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(0xff333333);
        mTextPaint.setAlpha(255-alpha);
        int x = mIconRect.left+mIconRect.width()/2-mTextbound.width()/2;
        int y = mIconRect.bottom + mTextbound.height();

        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     * 在内存中绘制可变的Icon
     * @param alpha
     */
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);//去锯齿
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);

        mCanvas.drawRect(mIconRect, mPaint);//绘制纯色
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //绘制Icon
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setIconAlpha(float alpha){
        this.mAlpha = alpha;
        invalidateView();//重绘制
    }

    /**
     * 重绘制
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.getMainLooper()){
            invalidate();
        }else {
            postInvalidate();
        }
    }
    //原本存储的Boundle
    private static final String INSTANCE_STATUS = "instance_status";
    //我们自己需要存储的东西
    private static final String STATUS_ALPHA = "status_alpha";

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        //我们自己存储的
        if (state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return ;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS,super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA,mAlpha);
        return bundle;
    }
}
