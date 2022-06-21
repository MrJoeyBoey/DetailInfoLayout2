package com.j.detailinfolayout2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.j.detailinfolayout2.holder.BaseViewHolder;
import com.j.detailinfolayout2.utils.MediaUtil;
import com.j.detailinfolayout2.utils.TextViewUtil;
import com.j.detailinfolayout2.utils.ViewUtil;
import com.j.mediaview.MediaView;
import com.j.mediaview.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailInfoLayout2 extends RecyclerView {

    private final Context context;

    private MyMultipleRecyclerAdapter<DetailInfo> detailInfoAdapter;
    private DetailInfoDividerDecoration detailInfoDividerDecoration;

    private int itemBackgroundColor;
    private float itemTextSize;

    private List<DetailInfo> detailInfos;

    private OnDetailInfoItemClickListener onDetailInfoItemClickListener;

    public DetailInfoLayout2(@NonNull Context context) {
        this(context, null);
    }

    public DetailInfoLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailInfoLayout2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initView();
    }

    private void initView(){
        this.setLayoutManager(new LinearLayoutManager(context));
        this.setOverScrollMode(OVER_SCROLL_NEVER);
        detailInfoDividerDecoration = new DetailInfoDividerDecoration(context);
        this.addItemDecoration(detailInfoDividerDecoration);

        itemBackgroundColor = Color.WHITE;
        itemTextSize = 17;

        detailInfoAdapter = new MyMultipleRecyclerAdapter<DetailInfo>(context,
                R.layout.adapter_detail_info, R.layout.adapter_detail_info2, R.layout.adapter_detail_info3) {
            @Override
            public void bindView(BaseViewHolder holder, DetailInfo detailInfo, int position) {
                LinearLayout llDetailInfo = holder.getView(R.id.ll_detail_info);
                TextView tvInfoKey = holder.getView(R.id.tv_info_key);

                tvInfoKey.setText(detailInfo.getKey());
                tvInfoKey.setTextSize(detailInfo.getKeyTextBuilder().getTextSize() == -1 ? itemTextSize : detailInfo.getKeyTextBuilder().getTextSize());
                tvInfoKey.setTextColor(detailInfo.getKeyTextBuilder().getTextColor() == -1 ? Color.BLACK : detailInfo.getKeyTextBuilder().getTextColor());
                Linkify.addLinks(tvInfoKey, detailInfo.getKeyTextBuilder().getMask() == -1 ? 0 : detailInfo.getKeyTextBuilder().getMask());
                TextViewUtil.setDrawableLeft(context,
                        tvInfoKey,
                        detailInfo.getLeftDrawableRes()
                );

                switch (detailInfo.getValueType()) {
                    case TEXT:
                        TextView tvInfoValue = holder.getView(R.id.tv_info_value);
                        tvInfoValue.setText(detailInfo.getValue());
                        tvInfoValue.setTextSize(detailInfo.getValueTextBuilder().getTextSize() == -1 ? itemTextSize : detailInfo.getValueTextBuilder().getTextSize());
                        tvInfoValue.setTextColor(detailInfo.getValueTextBuilder().getTextColor() == -1 ? Color.BLACK : detailInfo.getValueTextBuilder().getTextColor());
                        Linkify.addLinks(tvInfoValue, detailInfo.getValueTextBuilder().getMask() == -1 ? 0 : detailInfo.getValueTextBuilder().getMask());
                        TextViewUtil.setDrawableRight(context,
                                tvInfoValue,
                                detailInfo.getRightDrawableRes()
                        );
                        tvInfoValue.post(() -> tvInfoValue.setGravity(tvInfoValue.getLineCount() > 1 ? Gravity.START : Gravity.END));
                        break;
                    case RICH_TEXT:
                        TextView tvInfoValue2 = holder.getView(R.id.tv_info_value);
                        tvInfoValue2.setText(detailInfo.getValue());
                        tvInfoValue2.setTextSize(detailInfo.getValueTextBuilder().getTextSize() == -1 ? itemTextSize : detailInfo.getValueTextBuilder().getTextSize());
                        tvInfoValue2.setTextColor(detailInfo.getValueTextBuilder().getTextColor() == -1 ? Color.BLACK : detailInfo.getValueTextBuilder().getTextColor());
                        Linkify.addLinks(tvInfoValue2, detailInfo.getValueTextBuilder().getMask() == -1 ? 0 : detailInfo.getValueTextBuilder().getMask());
                        TextViewUtil.setDrawableRight(context,
                                tvInfoValue2,
                                detailInfo.getRightDrawableRes()
                        );
                        break;
                    case PICTURE:
                        MediaView mediaView = holder.getView(R.id.mv_info_value);
                        mediaView.setType(MediaView.ONLY_VIEW);
                        mediaView.setMedias(detailInfo.getKey(), MediaUtil.asMedia(detailInfo.getKey(), detailInfo.getValue()));
                        break;
                }

                ViewUtil.setMarginTop(llDetailInfo, detailInfo.getFlag() == Flag.Next ? 20 : 0);
                llDetailInfo.setBackgroundColor(itemBackgroundColor);
                llDetailInfo.setOnClickListener(view -> {
                    if (onDetailInfoItemClickListener != null) onDetailInfoItemClickListener.onDetailInfoItemClick(detailInfo.getKey(), detailInfo.getValue());
                });
            }

            @Override
            public int getItemViewType(int position) {
                ValueType valueType = getItem(position).getValueType();
                switch (valueType) {
                    case TEXT: return 0;
                    case RICH_TEXT: return 1;
                    case PICTURE: return 2;
                    default: return 0;
                }
            }
        };
        this.setAdapter(detailInfoAdapter);
    }

    public void setDetailInfo(List<DetailInfo> detailInfos){
        this.detailInfos = detailInfos;
        detailInfoAdapter.update(detailInfos);
        detailInfoDividerDecoration.setDetailInfos(detailInfos);
    }

    public List<DetailInfo> getDetailInfos() {
        return detailInfos;
    }

    public void setItemBackgroundColor(int itemBackgroundColor) {
        this.itemBackgroundColor = itemBackgroundColor;
    }

    public void setItemTextSize(float itemTextSize) {
        this.itemTextSize = itemTextSize;
    }

    public void setOnDetailInfoItemClickListener(OnDetailInfoItemClickListener onDetailInfoItemClickListener) {
        this.onDetailInfoItemClickListener = onDetailInfoItemClickListener;
    }

    public interface OnDetailInfoItemClickListener {
        void onDetailInfoItemClick(String key, String value);
    }

    public enum Flag {
        Next,
        Normal,
        Last
    }

    public enum ValueType {
        TEXT,
        RICH_TEXT,
        PICTURE
    }

    public static class TextBuilder {
        private int textSize;
        private int textColor;
        private int mask;

        public TextBuilder() {
            textSize = -1;
            textColor = -1;
            mask = -1;
        }

        public int getTextSize() {
            return textSize;
        }

        public TextBuilder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public int getTextColor() {
            return textColor;
        }

        public TextBuilder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public int getMask() {
            return mask;
        }

        public TextBuilder setMask(int mask) {
            this.mask = mask;
            return this;
        }
    }

    public static class DetailInfo {
        private int leftDrawableRes;
        private String key;
        private TextBuilder keyTextBuilder;

        private String value;
        private int rightDrawableRes;
        private TextBuilder valueTextBuilder;
        private ValueType valueType;

        private Flag flag;

        public DetailInfo(int leftDrawableRes, String key, TextBuilder keyTextBuilder,
                          String value, int rightDrawableRes, TextBuilder valueTextBuilder, ValueType valueType,
                          Flag flag) {
            this.leftDrawableRes = leftDrawableRes;
            this.key = key;
            this.keyTextBuilder = keyTextBuilder;
            this.value = value;
            this.rightDrawableRes = rightDrawableRes;
            this.valueTextBuilder = valueTextBuilder;
            this.valueType = valueType;
            this.flag = flag;
        }

        public int getLeftDrawableRes() {
            return leftDrawableRes;
        }

        public void setLeftDrawableRes(int leftDrawableRes) {
            this.leftDrawableRes = leftDrawableRes;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public TextBuilder getKeyTextBuilder() {
            return keyTextBuilder;
        }

        public void setKeyTextBuilder(TextBuilder keyTextBuilder) {
            this.keyTextBuilder = keyTextBuilder;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getRightDrawableRes() {
            return rightDrawableRes;
        }

        public void setRightDrawableRes(int rightDrawableRes) {
            this.rightDrawableRes = rightDrawableRes;
        }

        public TextBuilder getValueTextBuilder() {
            return valueTextBuilder;
        }

        public void setValueTextBuilder(TextBuilder valueTextBuilder) {
            this.valueTextBuilder = valueTextBuilder;
        }

        public ValueType getValueType() {
            return valueType;
        }

        public void setValueType(ValueType valueType) {
            this.valueType = valueType;
        }

        public Flag getFlag() {
            return flag;
        }

        public void setFlag(Flag flag) {
            this.flag = flag;
        }
    }

    public static class Builder {
        private final List<DetailInfo> detailInfos;

        public Builder() {
            detailInfos = new ArrayList<>();
        }

        public Builder add(int leftDrawableRes, String key, String value){
            return add(leftDrawableRes, key, value, Flag.Normal,true);
        }
        public Builder add(int leftDrawableRes, String key, String value, boolean visible){
            return add(leftDrawableRes, key, value, Flag.Normal, visible);
        }
        public Builder add(int leftDrawableRes, String key, String value, Flag flag){
            return add(leftDrawableRes, key, value, flag, true);
        }
        public Builder add(int leftDrawableRes, String key, String value, Flag flag, boolean visible){
            return add(leftDrawableRes, key, new TextBuilder(), value,0, new TextBuilder(), ValueType.TEXT, flag, visible);
        }

        public Builder add(int leftDrawableRes, String key, String value, int rightDrawableRes){
            return add(leftDrawableRes, key, value, rightDrawableRes, Flag.Normal,true);
        }
        public Builder add(int leftDrawableRes, String key, String value, int rightDrawableRes, Flag flag){
            return add(leftDrawableRes, key, value, rightDrawableRes, flag, true);
        }
        public Builder add(int leftDrawableRes, String key, String value, int rightDrawableRes, boolean visible){
            return add(leftDrawableRes, key, value, rightDrawableRes, Flag.Normal, visible);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value, int rightDrawableRes,
                           Flag flag, boolean visible){
            return add(leftDrawableRes, key, new TextBuilder(), value, rightDrawableRes, new TextBuilder(), ValueType.TEXT, flag, visible);
        }

        public Builder add(String key, String value){
            return add(key, value, new TextBuilder());
        }
        public Builder add(String key, String value, TextBuilder valueTextBuilder){
            return add(0, key, value, valueTextBuilder);
        }
        public Builder add(int leftDrawableRes, String key, String value, TextBuilder valueTextBuilder){
            return add(leftDrawableRes, key, value, valueTextBuilder, Flag.Normal, true);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value, TextBuilder valueTextBuilder,
                           Flag flag, boolean visible){
            return add(leftDrawableRes, key, new TextBuilder(), value, 0, valueTextBuilder, ValueType.TEXT, flag, visible);
        }

        public Builder add(int leftDrawableRes, String key, String value,  ValueType valueType){
            return add(leftDrawableRes, key, value, valueType, Flag.Normal, true);
        }
        public Builder add(int leftDrawableRes, String key, String value,  ValueType valueType, Flag flag){
            return add(leftDrawableRes, key, value, valueType, flag, true);
        }
        public Builder add(int leftDrawableRes, String key, String value,  ValueType valueType, boolean visible){
            return add(leftDrawableRes, key, value, valueType, Flag.Normal, visible);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value,  ValueType valueType,
                           Flag flag, boolean visible){
            return add(leftDrawableRes, key, value, new TextBuilder(), valueType, flag, visible);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value,  TextBuilder valueTextBuilder, ValueType valueType){
            return add(leftDrawableRes, key, new TextBuilder(), value, 0, valueTextBuilder, valueType, Flag.Normal, true);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value,  TextBuilder valueTextBuilder, ValueType valueType,
                           Flag flag){
            return add(leftDrawableRes, key, new TextBuilder(), value, 0, valueTextBuilder, valueType, flag, true);
        }
        public Builder add(int leftDrawableRes, String key,
                           String value,  TextBuilder valueTextBuilder, ValueType valueType,
                           Flag flag, boolean visible){
            return add(leftDrawableRes, key, new TextBuilder(), value, 0, valueTextBuilder, valueType, flag, visible);
        }

        public Builder add(int leftDrawableRes, String key, TextBuilder keyTextBuilder,
                           String value, int rightDrawableRes, TextBuilder valueTextBuilder, ValueType valueType,
                           Flag flag, boolean visible){
            if (visible) detailInfos.add(new DetailInfo(leftDrawableRes, key, keyTextBuilder, value, rightDrawableRes, valueTextBuilder, valueType, flag));
            if (visible && detailInfos.size() > 1 && flag == Flag.Next) {
                DetailInfo lastDetailInfo = detailInfos.get(detailInfos.size() - 2);
                lastDetailInfo.setFlag(Flag.Last);
            }
            return this;
        }

        public Builder remove(String key, String value){
            for (DetailInfo detailInfo : detailInfos) {
                if (detailInfo.getKey().equals(key) && detailInfo.getValue().equals(value)) {
                    detailInfos.remove(detailInfo);
                    break;
                }
            }
            return this;
        }

        public List<DetailInfo> build(){
            return detailInfos;
        }
    }

    private static class DetailInfoDividerDecoration extends DividerItemDecoration {

        private final Rect mBounds = new Rect();

        private List<DetailInfo> detailInfos;

        public DetailInfoDividerDecoration(Context context) {
            super(context,  DividerItemDecoration.VERTICAL);
            detailInfos = new ArrayList<>();
        }

        public void setDetailInfos(List<DetailInfo> detailInfos) {
            this.detailInfos = detailInfos;
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, State state) {
            Drawable mDivider = getDrawable();
            if (parent.getLayoutManager() == null || mDivider == null) {
                return;
            }

            canvas.save();
            final int left;
            final int right;
            //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                canvas.clipRect(left, parent.getPaddingTop(), right,
                        parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0;
                right = parent.getWidth();
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) { //取消最后一个item的分割线
                if (detailInfos.get(i).getFlag() == Flag.Last) continue; // 取消flag为last的分割线，其他照旧

                final View child = parent.getChildAt(i);
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left + DisplayUtil.dp2px(10), top, right - DisplayUtil.dp2px(10), bottom); //left和right增加间隔10dp
                mDivider.draw(canvas);
            }
            canvas.restore();
        }
    }

}
