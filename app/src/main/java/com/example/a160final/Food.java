package com.example.a160final;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class Food implements Parcelable{
    private String name;
    private String price;
    private String info;
    private double priceDouble;
    private int imageId;

    public Food(String name, String price, double priceDouble, String info, int imageId) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.priceDouble = priceDouble;
        this.imageId = imageId;
    }

    protected Food(Parcel in) {
        name = in.readString();
        price = in.readString();
        info = in.readString();
        priceDouble = in.readDouble();
        imageId = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public double getPriceDouble() { return priceDouble; }

    public int getImageId() {return imageId; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(info);
        dest.writeDouble(priceDouble);
        dest.writeInt(imageId);
    }
}
