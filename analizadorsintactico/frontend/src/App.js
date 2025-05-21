import React from 'react';
import LexicAnalyzer from './components/LexicalAnalyzer';
import VideoBackground from './components/VideoBackground';
import './index.css';

function App() {
  return (
    <div className="App">
      <VideoBackground />
      <LexicAnalyzer />
    </div>
  );
}

export default App;