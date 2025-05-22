import { useState } from 'react';

function App() {
  const [texto, setTexto] = useState('');
  const [resultado, setResultado] = useState(null);
  const [cargando, setCargando] = useState(false);

  const manejarCambio = (e) => {
    setTexto(e.target.value);
  };

  const analizarExpresion = async (useGet = false) => {
    if (!texto.trim()) {
      setResultado(null);
      return;
    }

    setCargando(true);
    try {
      let response;
      
      if (useGet) {
        // Usar GET con parámetros de consulta
        const encodedExpression = encodeURIComponent(texto);
        response = await fetch(`http://localhost:8080/api/analyzer/analyze?expression=${encodedExpression}`, {
          method: 'GET',
          headers: {
            'Accept': 'application/json',
          }
        });
      } else {
        // Usar POST con JSON body
        response = await fetch('http://localhost:8080/api/analyzer/analyze', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
          },
          body: JSON.stringify({
            expression: texto
          })
        });
      }

      if (!response.ok) {
        throw new Error(`HTTP ${response.status}: ${response.statusText}`);
      }

      const data = await response.json();
      setResultado(data);
    } catch (error) {
      console.error('Error de conexión:', error);
      setResultado({
        success: false,
        error: 'Error de conexión con el servidor: ' + error.message,
        hasErrors: true
      });
    } finally {
      setCargando(false);
    }
  };

  // Análisis automático cuando cambia el texto (con debounce)
  useState(() => {
    const timeoutId = setTimeout(() => {
      if (texto.trim()) {
        analizarExpresion();
      } else {
        setResultado(null);
      }
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [texto]);

  const contarPalabras = (str) => {
    return str.trim() === '' ? 0 : str.trim().split(/\s+/).length;
  };

  return (
    <div className="min-h-screen bg-gray-50 p-6">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">
          Analizador de Expresiones Algebraicas
        </h1>

        <div className="bg-white rounded-lg shadow-md p-6 mb-6">
          <h2 className="text-xl font-semibold mb-4">Entrada</h2>
          <textarea
            value={texto}
            onChange={manejarCambio}
            placeholder="Escribe una expresión algebraica aquí... Ejemplo: x => 2+3;"
            rows={6}
            className="w-full p-3 border border-gray-300 rounded-md text-lg font-mono resize-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
          <div className="mt-4 flex justify-between items-center">
            <div className="text-sm text-gray-600">
              <span>Caracteres: {texto.length} | </span>
              <span>Palabras: {contarPalabras(texto)}</span>
            </div>
            <div className="space-x-2">
              <button 
                onClick={() => analizarExpresion(true)}
                disabled={cargando || !texto.trim()}
                className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 disabled:bg-gray-400 disabled:cursor-not-allowed"
              >
                Analizar (GET)
              </button>
            </div>
          </div>
        </div>

        {cargando && (
          <div className="bg-blue-50 border border-blue-200 rounded-lg p-4 mb-6">
            <div className="flex items-center">
              <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-600 mr-3"></div>
              <span className="text-blue-700">Analizando expresión...</span>
            </div>
          </div>
        )}

        {resultado && (
          <div className="space-y-6">
            {/* Estado del análisis */}
            <div className={`rounded-lg p-4 ${
              resultado.success 
                ? 'bg-green-50 border border-green-200' 
                : 'bg-red-50 border border-red-200'
            }`}>
              <h3 className="font-semibold mb-2">
                {resultado.success ? '✅ Análisis Exitoso' : '❌ Error en el Análisis'}
              </h3>
              {resultado.success ? (
                <p className="text-green-700">{resultado.analysisMessage}</p>
              ) : (
                <p className="text-red-700">{resultado.error}</p>
              )}
            </div>

            {resultado.success && (
              <>
                {/* Estadísticas */}
                <div className="bg-white rounded-lg shadow-md p-6">
                  <h3 className="text-xl font-semibold mb-4">Estadísticas</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="bg-blue-50 p-4 rounded-lg">
                      <div className="text-2xl font-bold text-blue-600">
                        {resultado.tokenCount}
                      </div>
                      <div className="text-blue-700">Tokens</div>
                    </div>
                    <div className="bg-green-50 p-4 rounded-lg">
                      <div className="text-2xl font-bold text-green-600">
                        {resultado.characterCount}
                      </div>
                      <div className="text-green-700">Caracteres</div>
                    </div>
                  </div>
                </div>

                {/* Tokens */}
                {resultado.tokens && resultado.tokens.length > 0 && (
                  <div className="bg-white rounded-lg shadow-md p-6">
                    <h3 className="text-xl font-semibold mb-4">Tokens Identificados</h3>
                    <div className="overflow-x-auto">
                      <table className="min-w-full table-auto">
                        <thead>
                          <tr className="bg-gray-50">
                            <th className="px-4 py-2 text-left">Tipo</th>
                            <th className="px-4 py-2 text-left">Texto</th>
                            <th className="px-4 py-2 text-left">Línea</th>
                            <th className="px-4 py-2 text-left">Columna</th>
                          </tr>
                        </thead>
                        <tbody>
                          {resultado.tokens.map((token, index) => (
                            <tr key={index} className="border-b border-gray-200">
                              <td className="px-4 py-2 font-mono text-sm bg-blue-50 text-blue-800 rounded">
                                {token.type}
                              </td>
                              <td className="px-4 py-2 font-mono font-bold">
                                {token.text}
                              </td>
                              <td className="px-4 py-2 text-gray-600">
                                {token.line}
                              </td>
                              <td className="px-4 py-2 text-gray-600">
                                {token.column}
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  </div>
                )}

                {/* Árbol sintáctico */}
                {resultado.syntaxTree && (
                  <div className="bg-white rounded-lg shadow-md p-6">
                    <h3 className="text-xl font-semibold mb-4">Árbol Sintáctico</h3>
                    <div className="bg-gray-50 p-4 rounded-lg">
                      <pre className="text-sm font-mono overflow-x-auto whitespace-pre-wrap">
                        {resultado.syntaxTree}
                      </pre>
                    </div>
                  </div>
                )}
              </>
            )}
          </div>
        )}

        <div className="mt-8 text-center text-gray-500 text-sm">
          Analizador de Expresiones Algebraicas con ANTLR
        </div>
      </div>
    </div>
  );
}

export default App;