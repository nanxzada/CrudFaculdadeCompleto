const formularioA = document.querySelector(".formAluno")
const formularioD = document.querySelector(".formDisc")
//Variaveis Aluno
const inome = document.querySelector(".nomeAluno")
const icurso = document.querySelector(".curso")
const ivalor = document.querySelector(".valor")
//Variaveis Disciplina
const iNDisciplina = document.querySelector(".nomeDisciplina")
const idesc = document.querySelector(".descricao")
const iprof = document.querySelector(".professor")

function cadastrar() {
    fetch("http://localhost:8000/cadastrar",
        {
            headers:{
                'Accept':'application/json',
                'Content-Type': 'aplication/json'
            },
            method: "POST",
            body: JSON.stringify({
                nomeAluno: inome.value,
                curso: icurso.value,
                valor: ivalor.value,

                nomeDisciplina: iNDisciplina.value,
                descricao: idesc.value,
                professor: iprof.value
            })
        })
        .then(function (res) { console.log(res) })
        .catch(function (res) { console.log(res) })
}

function limpar () {
        inome.value = ""
        icurso.value = ""
        ivalor.value = ""

        iNDisciplina.value = ""
        idesc.value = ""
        iprof.value = ""

}

formularioA.addEventListener("submit", function(event){
    event.preventDefault();

    cadastrar()
    limpar()
})
formularioD.addEventListener("submit", function (event){
    event.preventDefault();
    
    cadastrar()
    limpar()
})