package com.example.myfirstapp.Image;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    private  String imageUrl;

    public Gallery(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public  String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static List<Gallery>getGlideImages(){
        ArrayList<Gallery> galleries = new ArrayList<>();
        galleries.add(new Gallery("https://i.pinimg.com/564x/bb/a5/37/bba53728dcb0374204de303a7f604623.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/4f/a4/31/4fa4314526c2ebe20b5bd2d2f63120fc.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/72/1e/c8/721ec8a0793eb7f1f3f5e704db3cd0f0.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/c4/2a/73/c42a73de69418ed65e5c91c4ffb323b4.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/63/50/e7/6350e781b3fc7489905e029653d5469d.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/0a/b4/4d/0ab44d2b6cf42225d850fe0224c32b27.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/79/82/f6/7982f6b391578e1e69c89a11667e89d1.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/4f/95/88/4f95888fd5770b6de199fe5e3eebfd44.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/a7/a0/44/a7a044984146fb56d50da27dab7d2a20.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/09/46/0b/09460b3ee205cc8ee027e6c1c26196f6.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/5c/a1/44/5ca144cd26084f06336292c8be19bafe.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/fa/99/95/fa9995d28f906ee35a2733af00387144.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/564x/bb/a5/37/bba53728dcb0374204de303a7f604623.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/33/cc/e8/33cce898b922df181752a7368d5f584e.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/564x/bb/a5/37/bba53728dcb0374204de303a7f604623.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/4f/a4/31/4fa4314526c2ebe20b5bd2d2f63120fc.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/72/1e/c8/721ec8a0793eb7f1f3f5e704db3cd0f0.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/c4/2a/73/c42a73de69418ed65e5c91c4ffb323b4.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/5c/a1/44/5ca144cd26084f06336292c8be19bafe.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/fa/99/95/fa9995d28f906ee35a2733af00387144.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/564x/bb/a5/37/bba53728dcb0374204de303a7f604623.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/33/cc/e8/33cce898b922df181752a7368d5f584e.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/564x/bb/a5/37/bba53728dcb0374204de303a7f604623.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/4f/a4/31/4fa4314526c2ebe20b5bd2d2f63120fc.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/72/1e/c8/721ec8a0793eb7f1f3f5e704db3cd0f0.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/c4/2a/73/c42a73de69418ed65e5c91c4ffb323b4.jpg"));
        galleries.add(new Gallery("https://i.pinimg.com/236x/4f/a4/31/4fa4314526c2ebe20b5bd2d2f63120fc.jpg"));

        return galleries;
    }
}