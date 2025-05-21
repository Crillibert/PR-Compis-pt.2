import React from "react";

const VideoBackground = () => {
  return (
    <video 
      autoPlay 
      loop 
      muted 
      className="video-background"
    >
      <source src="/Matrix.mp4" type="video/mp4" />
      Tu navegador no soporta videos HTML5
    </video>
  );
};

export default VideoBackground;