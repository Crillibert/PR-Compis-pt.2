import React, { useRef } from "react";
import "../index.css";

const LexicAnalyzer = () => {
  const [input, setInput] = React.useState("");
  const [tokens, setTokens] = React.useState([]);
  const [fileName, setFileName] = React.useState("Ningún archivo seleccionado");
  const fileInputRef = useRef(null);

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setFileName(file.name);
      const reader = new FileReader();
      reader.onload = (event) => {
        setInput(event.target.result);
      };
      reader.readAsText(file);
    }
  };

  const analyze = () => {
    const tokenized = input.split(/\s+/).filter(token => token.length > 0);
    setTokens(tokenized);
  };

  return (
    <div className="analyzer-container">
      <h1>Analizador Léxico</h1>
      
      {/* Sección de selección de archivos */}
      <div className="file-selector">
        <input
          type="file"
          ref={fileInputRef}
          onChange={handleFileChange}
          accept=".txt"
          style={{ display: 'none' }}
        />
        <button 
          className="file-button"
          onClick={() => fileInputRef.current.click()}
        >
          Seleccionar archivo
        </button>
        <span className="file-name">{fileName}</span>
      </div>

      <textarea
        className="code-input"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Escribe tu código aquí..."
      />
      
      <button className="analyze-button" onClick={analyze}>
        Analizar
      </button>
      
      <div className="tokens-list">
        {tokens.map((token, index) => (
          <div key={index} className="token">
            {token}
          </div>
        ))}
      </div>
    </div>
  );
};

export default LexicAnalyzer;