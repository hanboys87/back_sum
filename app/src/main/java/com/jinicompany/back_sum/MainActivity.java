package com.jinicompany.back_sum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Context mCotext;
    ImageView iv,iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv_sum);
        iv1=findViewById(R.id.iv_sum1);

        mCotext=this;
        Bitmap back  = BitmapFactory.decodeResource(mCotext.getResources(), R.drawable.face_540);
        Bitmap back1  = BitmapFactory.decodeResource(mCotext.getResources(), R.drawable.left1);

        Bitmap back2 = Bitmap.createBitmap(back,0,0,100,100);

        Bitmap re_back = Bitmap.createScaledBitmap(back,1000,500,false);
        Bitmap re_back1 = Bitmap.createScaledBitmap(back1,1000,500,false);

        Bitmap res = overlayMark(re_back,re_back1);

        Bitmap CropBitmap = cropBitmap(back);
        //iv.setImageBitmap(back);
        iv1.setImageBitmap(CropBitmap);
    }


    static public Bitmap cropBitmap(Bitmap original) {
        Bitmap result = Bitmap.createBitmap(original
                , 0 //X 시작위치 (원본의 4/1지점)
                , original.getHeight() / 10 //Y 시작위치 (원본의 4/1지점)
                , original.getWidth()  // 넓이 (원본의 절반 크기)
                , original.getHeight()-original.getHeight() / 10); // 높이 (원본의 절반 크기)
        if (result != original) {
            original.recycle();
        }
        return result;
    }

    private Bitmap overlayMark(Bitmap bmp1, Bitmap bmp2)

    {

        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());

        Canvas canvas = new Canvas(bmOverlay);

        canvas.drawBitmap(bmp1, 100, 150, null);

        canvas.drawBitmap(bmp2, 0.0f, 0.0f, null);

        return bmOverlay;

    }



//    public Bitmap overlayBitmap(Bitmap original, Context context) {
//
//
//        Bitmap back  = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
//        Bitmap back1  = BitmapFactory.decodeResource(context.getResources(), R.drawable.back1);
//
//        double aspectRatio = (double) original.getHeight() / (double) original.getWidth();
//
//        int MAX_LENGTH = 120;
//
//        int targetWidth, targetHeight;
//        int startW = 0;
//        int startH = 0;
//        Bitmap originalResizeBmp;
//
//        if (aspectRatio >= 1) { //세로가 긴 경우
//            targetWidth = MAX_LENGTH;
//            targetHeight = (int) (targetWidth * aspectRatio);
//            startH = (targetHeight - targetWidth) / 2;
//
//        } else { //가로가 긴 경우
//            targetHeight = MAX_LENGTH;
//            targetWidth = (int) (targetHeight / aspectRatio);
//            startW = (targetWidth - targetHeight) / 2;
//        }
//
//        //하단 비트맵
//        originalResizeBmp = Bitmap.createScaledBitmap(original, targetWidth, targetHeight, false);
//        originalResizeBmp = originalResizeBmp.createBitmap(originalResizeBmp, startW, startH
//                , (targetWidth > targetHeight ? targetHeight : targetWidth)
//                , (targetWidth > targetHeight ? targetHeight : targetWidth));
//
//        //상단 비트맵
//        Bitmap overlayBmp = Bitmap.createScaledBitmap(getOverlayBitmap(context)
//                , (targetWidth > targetHeight ? targetHeight : targetWidth)
//                , (targetWidth > targetHeight ? targetHeight : targetWidth)
//                , false);
//
//        //결과값 저장을 위한 Bitmap
//        Bitmap resultOverlayBmp = Bitmap.createBitmap(originalResizeBmp.getWidth()
//                , originalResizeBmp.getHeight()
//                , originalResizeBmp.getConfig());
//
//
//        //상단 비트맵에 알파값을 적용하기 위한 Paint
//        Paint alphaPaint = new Paint();
//        alphaPaint.setAlpha(125);
//
//        //캔버스를 통해 비트맵을 겹치기한다.
//        Canvas canvas = new Canvas(resultOverlayBmp);
//        canvas.drawBitmap(originalResizeBmp, new Matrix(), null);
//        canvas.drawBitmap(overlayBmp, new Matrix(), alphaPaint);
//
//        if (originalResizeBmp != original) {
//            original.recycle();
//        }
//        if (originalResizeBmp != resultOverlayBmp) {
//            originalResizeBmp.recycle();
//        }
//        if (overlayBmp != resultOverlayBmp) {
//            overlayBmp.recycle();
//        }
//
//        return resultOverlayBmp;
//    }




}