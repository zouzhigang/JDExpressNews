# 高仿JD快报轮播效果实现

#### 使用步骤1

```

   <com.summer.jdexpressnews.JDExpressNewsView
        android:id="@+id/jd_expressnews_tip"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
```

#### 使用步骤2

```
        JDExpressNewsView jd_expressnews_tip = (JDExpressNewsView) findViewById(R.id.jd_expressnews_tip);
        List<String> notices = new ArrayList<>();
        notices.add("狂欢618，购机送好礼");
        notices.add("3C嗨购 年终盛典");
        notices.add("品牌狂欢城 亿万红包嗨翻天");
        jd_expressnews_tip.addNotice(notices);
        jd_expressnews_tip.startFlipping();
        jd_expressnews_tip.setOnNoticeClickListener(new JDExpressNewsView.OnJDExpressNewsClickListener() {
            @Override
            public void onJDExpressNewsClick(int position, String notice) {
                Toast.makeText(MainActivity.this,""+notice,Toast.LENGTH_LONG).show();
            }
        });
```

#### JDExpressNewsView

```
public class JDExpressNewsView  extends ViewFlipper implements View.OnClickListener{
    private Context mContext;
    private List<String> mNotices;

    public JDExpressNewsView(Context context) {
        super(context);
    }

    public JDExpressNewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        mContext = context;
        // 轮播间隔时间为3s
        setFlipInterval(3000);
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        // 设置enter和leave动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.express_new_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.express_new_out));
    }

    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
    public void addNotice(List<String> notices) {
        mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            // 根据公告内容构建一个TextView
            String notice = notices.get(i);
            TextView textView = new TextView(mContext);
            textView.setSingleLine();
            textView.setText(notice);
            textView.setTextSize(16f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setGravity(Gravity.CENTER);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(i);
            textView.setOnClickListener(this);
            // 添加到ViewFlipper
            JDExpressNewsView.this.addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        String notice = (String) mNotices.get(position);
        if (mOnJDExpressNewsClickListener != null) {
            mOnJDExpressNewsClickListener.onJDExpressNewsClick(position, notice);
        }
    }
    /**
     * 通知点击监听接口
     */
    public interface OnJDExpressNewsClickListener {
        void onJDExpressNewsClick(int position, String notice);
    }

    private OnJDExpressNewsClickListener mOnJDExpressNewsClickListener;

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnJDExpressNewsClickListener onNoticeClickListener) {
        mOnJDExpressNewsClickListener = onNoticeClickListener;
    }
    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                mContext.getResources().getDisplayMetrics());
    }
}
```
