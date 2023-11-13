<script>
// @ts-nocheck

    const API_URL = "http://localhost:3000/translator";

    var txtFile = null;
    let txtFileContent = null;
    let pythonFileContent = null;

    function handleFileInput(event) {
    txtFile = event.target.files[0];
    console.log(txtFile);

    if (txtFile) {
      // Read the content of the selected file for preview
      const reader = new FileReader();
      reader.onload = function (e) {
        txtFileContent = e.target.result;
      };
      reader.readAsText(txtFile);
    } else {
      txtFileContent = null;
    }
  }
    async function getPythonFile(){

        const formData = new FormData();
		formData.append('file', txtFile);

        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                
            },
            
            body: formData
        });

        if(response.ok){
            //Download file 
            pythonFileContent = await response.text();
        }
        console.log(response);
    }
</script>

<nav class="navbar navbar-light bg-light">
    <span class="navbar-brand mb-0 h1">Traductor de codigo inventado a Python</span>
  </nav>
<main class="container-fluid" >
   <div class=" mt-4">
    <label for="file" class="form-label">Selecciona el archivo a traducir</label>
    <input type="file" class="form-control" on:change={handleFileInput} accept=".txt">
    <button on:click={getPythonFile} class="btn btn-primary mt-2" >Traducir</button>
   </div>

    <div class="d-flex justify-content-center mt-3">
        <div class="px-5 overflow-auto">
            {#if txtFile !== null}
            <h2>Codigo original</h2>
            <pre class="form-control overflow-auto">{txtFileContent}</pre>
            {/if}
            
        </div>
        <div class="px-5 ">
            
            {#if pythonFileContent !== null}
            <h2>Codigo traducido</h2>
            <pre class="form-control overflow-auto">{pythonFileContent}</pre>
            {/if}
        </div>
    </div>
</main>