import React, { useRef, useState } from "react";
import "../index.css"; // Asegúrate de tener este archivo

const LexicalAnalyzer = () => {
  const [input, setInput] = useState("");
  const [resultado, setResultado] = useState({ tokens: [], arbol: "" });
  const [fileName, setFileName] = useState("Ningún archivo seleccionado");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
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
    if (!input.trim()) {
      setError("Por favor ingresa código o selecciona un archivo");
      return;
    }

    setLoading(true);
    setError(null);
    
    try {
      const response = await fetch("http://localhost:8080/api/analizar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ codigoFuente: input }),
      });

      if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);
      
      const data = await response.json();
      
      if (data.status === "error") throw new Error(data.message);
      
      setResultado({
        tokens: data.tokens || [],
        arbol: data.arbol || "No se generó árbol sintáctico",
      });

    } catch (err) {
      console.error("Error en el análisis:", err);
      setError(`Error: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="lexical-analyzer-container">
      <h1 className="title">Analizador Léxico y Sintáctico</h1>
      
      <div className="file-selector">
        <input
          type="file"
          ref={fileInputRef}
          onChange={handleFileChange}
          accept=".txt,.js,.java"
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

      <div className="input-section">
        <textarea
          className="code-input"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Escribe tu código aquí..."
          rows={8}
        />
        <button 
          className="analyze-button"
          onClick={analyze} 
          disabled={loading}
        >
          {loading ? "Analizando..." : "Analizar"}
        </button>
      </div>

      {error && <div className="error-message">{error}</div>}
      
      <div className="results-section">
        {resultado.tokens.length > 0 && (
          <div className="tokens-result">
            <h2>Tokens:</h2>
            <table className="tokens-table">
              <thead>
                <tr>
                  <th>Línea</th>
                  <th>Columna</th>
                  <th>Tipo</th>
                  <th>Valor</th>
                </tr>
              </thead>
              <tbody>
                {resultado.tokens.map((token, index) => (
                  <tr key={index}>
                    <td>{token.line}</td>
                    <td>{token.column}</td>
                    <td>{token.type}</td>
                    <td>{token.text}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
        
        {resultado.arbol && (
          <div className="tree-result">
            <h2>Árbol Sintáctico:</h2>
            <pre>{resultado.arbol}</pre>
          </div>
        )}
      </div>
    </div>
  );
};

export default LexicalAnalyzer;