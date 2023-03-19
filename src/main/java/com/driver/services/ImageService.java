package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blogRepository2.findById(blogId).get());
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimensions = image.getDimensions();
        int imageDimensinH = Integer.parseInt(dimensions.substring(0,1));
        int imageDimensinV = Integer.parseInt(dimensions.substring(2));
        int screenDimensionH = Integer.parseInt(screenDimensions.substring(0,1));
        int screenDimensionV = Integer.parseInt(screenDimensions.substring(2));
        int imageSize = imageDimensinH*imageDimensinV;
        int screenSize = screenDimensionV*screenDimensionH;

        return screenSize/imageSize;
    }
}
