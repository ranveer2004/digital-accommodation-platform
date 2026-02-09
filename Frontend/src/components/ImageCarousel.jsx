// ImageCarousel.jsx
import React from "react";
import Slider from "react-slick";
import { Box, Card, CardMedia } from "@mui/material";

const ImageCarousel = ({ images }) => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 1000,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000,
  };

  return (
    <Box sx={{ width: "100%", maxWidth: "100%", margin: "auto" }}>
      <Slider {...settings}>
        {images.map((imgUrl, index) => (
          <Card key={index}>
            <CardMedia
              component="img"
              height="500"
              image={imgUrl}
              alt={`property-image-${index}`}
              sx={{ objectFit: "cover" }}
            />
          </Card>
        ))}
      </Slider>
    </Box>
  );
};

export default ImageCarousel;