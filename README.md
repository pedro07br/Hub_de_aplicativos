# Hub de Aplicativos - Trabalho Avaliativo

Este é um projeto desenvolvido para a disciplina de Desenvolvimento de Aplicações para Dispositivos Móveis. O aplicativo consiste em um hub central que dá acesso a quatro mini-aplicativos com diferentes funcionalidades.

## Screenshots

| Tela Principal | Tela de Seleção |
| :---: |:---:|
| [COLE O LINK DO SEU SCREENSHOT AQUI] | [COLE O LINK DO SEU SCREENSHOT AQUI] |

| Codificador | Pedra, Papel e Tesoura |
| :---: |:---:|
| [COLE O LINK DO SEU SCREENSHOT AQUI] | [COLE O LINK DO SEU SCREENSHOT AQUI] |

| Código Secreto | Randomizador |
| :---: |:---:|
| [COLE O LINK DO SEU SCREENSHOT AQUI] | [COLE O LINK DO SEU SCREENSHOT AQUI] |


## Funcionalidades Implementadas

O aplicativo principal serve como um menu para os seguintes mini-apps:

* **1. Codificador de Palavras**
    * Permite ao usuário inserir uma mensagem e uma chave numérica.
    * Codifica e decodifica o texto usando o algoritmo da Cifra de César.
    * Apresenta o resultado com animação e feedback sonoro.

* **2. Jogo de Pedra, Papel e Tesoura**
    * Jogo contra uma IA com escolhas aleatórias.
    * Sistema de "melhor de 3".
    * Exibe o vencedor de cada round com animação e apresenta uma tela de resultado final.

* **3. Jogo do Código Secreto**
    * O jogador deve adivinhar uma sequência de 4 dígitos gerada aleatoriamente.
    * A cada tentativa, o jogo fornece feedback sobre os dígitos (certo, posição errada ou errado), similar ao jogo Termo.
    * O histórico de tentativas é exibido em uma lista.
    * Ao vencer, exibe uma tela de sucesso com o tempo decorrido e o número de tentativas.

* **4. Randomizador de Palavras/Valores**
    * Permite ao usuário criar categorias personalizadas.
    * Permite adicionar e remover itens dentro de cada categoria.
    * Um botão permite sortear aleatoriamente um item de todas as categorias cadastradas.

## Tecnologias Utilizadas

* **Linguagem:** Java
* **IDE:** Android Studio
* **Principais Componentes Android:**
    * `RecyclerView` com `ListAdapter` e `DiffUtil`
    * `CardView` para layouts de item
    * `MediaPlayer` para gerenciamento de áudio
    * Animações de View (`alpha`, `scale`)
    * `Intent` para navegação e passagem de dados
    * Manipulação do Ciclo de Vida da `Activity`

## Autor

* **Nome:** Gabriel Ykaro Rodrigues da Silva
* **Matrícula:** 20241012000255
* **Curso:** Análise e Desenvolvimento de Sistemas
