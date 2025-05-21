import React, { useRef, useState } from "react";
import "../index.css";

const LexicAnalyzer = () => {
  const [input, setInput] = useState("");
  const [resultado, setResultado] = useState({ tokens: [], arbol: "" });
  const [fileName, setFileName] = useState("Ningún archivo seleccionado");
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

  const analyze = async () => {
    try {
      const response = await fetch("http://localhost:8081/api/analizar", {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: input,
      });
      const data = await response.json();
      setResultado({
        tokens: data.tokens, 
        arbol: data.arbol,
      });
    } catch (error) {
      console.error("Error al analizar:", error);
    }
  };

  return (
    <div className="analyzer-container">
      <h1>Analizador Léxico y Sintáctico</h1>
      
      <div className="file-selector">
        <input
          type="file"
          ref={fileInputRef}
          onChange={handleFileChange}
          accept=".txt"
          style={{ display: 'none' }}
        />
        <button onClick={() => fileInputRef.current.click()}>
          Seleccionar archivo
        </button>
        <span>{fileName}</span>
      </div>

      <textarea
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Escribe tu código aquí..."
      />
      
      <button onClick={analyze}>Analizar</button>
      
      <div className="results">
        <h2>Tokens:</h2>
        <pre>{resultado.tokens}</pre>
        
        <h2>Árbol Sintáctico:</h2>
        <pre>{resultado.arbol}</pre>
      </div>
    </div>
  );
};

export default LexicAnalyzer;