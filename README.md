# Projeto `Bravia Escape`

# Equipe: Objetos Desorientados
* `Lucas de Paula Soares` - `RA:201867`
* `Antonio Gabriel da Silva Fernandes` - `RA:231551`

# Estrutura de Arquivos e Pastas
~~~
├── README.md          <- apresentação do projeto
│
├── data               <- arquivos .csv das fases
│
├── src                <- projeto em Java no Eclipse
│   │
│   ├── src            <- arquivos-fonte do projeto (.java)
│   │
│   ├── assets         <- cópia das mídias usadas no projeto
│   │
│   ├── stages         <- cópia dos arquivos .csv das fases
│   │
│   ├── bin            <- arquivos em bytecode (.class)
│   │
│   └── README.md      <- instruções básicas de instalação/execução
│
└── assets             <- mídias usadas no projeto
~~~

# Descrição Resumida do Projeto
Bravia é uma jovem que foi capturada por monstros e precisa fugir da masmorra usando apenas uma tocha para se guiar na escuridão.

O jogo se passa em um grid celular onde estão marcadas a entrada e a saída daquela fase. Para alcançar a saída, será necessário encontrar chaves certas que abrem portas que liberam o caminho. O jogador pode se mover nas quatro direções, em células que não contêm obstáculos.

Na caverna, podemos encontrar também monstros que perseguem nossa heroína. Alguns deles rondam os corredores procurando por ela enquanto outros ficam de guarda escondidos até serem encontrados. Assim que entram no raio da tocha de Bravia, eles começam a persegui-la, até serem despistados. Os monstros se movem uma vez para cada movimento feito pelo jogador, e não conseguem passar por portões (mesmo destrancados).

Em fases mais longas, haverá uma fogueira que pode ser acesa por Bravia, que
funcionará como checkpoint. Essas fogueiras iluminam uma área a seu redor depois de
acesas, e estarão dentro de uma área cercada por paredes e acessível apenas por portões, o que as torna espaços seguros de monstros.


# Vídeos do Projeto
## Vídeo da Prévia
[![Vídeo da prévia](http://img.youtube.com/vi/I7GSFeWmWMA/0.jpg)](https://youtu.be/I7GSFeWmWMA)

## Vídeo do Jogo
[![Vídeo do jogo]()]()

# Slides do Projeto
## Slides da prévia
[Link para slides da prévia](https://docs.google.com/presentation/d/1SCRml4NGaG-R9hetl57H_yJrgA6-L83-OzihDUgYVMk/edit?usp=sharing)

## Slides da Apresentação Final
[Link para slides da apresentação final]()

# Relatório de Evolução
Ao longo do desenvolvimento do projeto, diversas mudanças foram feitas à medida que percebemos formas melhores de implementar certas mecânicas, ou simplesmente que certas mecânicas não eram estritamente necessárias para o jogo, simplesmente adicionando tempo de desenvolvimento sem necessariamente agregar valor.

O componente `MapGenerator` foi totalmente repensado, agora com o nome de `GameBuilder` (por implementar o design pattern Builder). Ele também agora interage com duas outras classes, `EnemyFactory` e `CellFactory` (que implementam o design pattern Factory). A ideia geral de gerar um objeto `Map` através de um arquivo .csv foi mantida, mas foi implementada de forma muito mais elegante usando esses design patterns. Ele também é responsável por instanciar um objeto `Bravia`, por isso se chama `GameBuilder`, não `MapBuilder`.

Foram criados dois tipos diferentes de `Enemy`, o `EnemyHunter`, que patrulha o mapa em busca do jogador, e o `EnemyGuardian`, que aguarda escondido até ser descoberto, e a classe `Enemy` agora é abstrata. Isso permite melhor expansibilidade e variedade de tipos de inimigos.

Foi criada a classe e componente `Checkpoint`, que lida com as informações de respawn de Bravia, ou seja, guarda o estado do jogo assim que o jogador aciona um checkpoint (fogueira ou nova fase) e fornece essas informações para que possam ser restauradas quando Bravia morre. Seus métodos são todos estáticos, o que facilita o acesso por diferentes classes.

Foi abandonada a ideia de ter baús com itens nas fases. Embora seja uma ideia interessante, tomaria muito tempo de desenvolvimento e acreditamos que o jogo já esteja perfeitamente funcional e divertido sem eles. Seria um bom ponto para começar caso o desenvolvimento seja retomado. Assim, todas as classes, métodos e atributos relacionados a essa mecânica foram excluídos.

A principal dificuldade durante o desenvolvimento foi decidir detalhes sobre o funcionamento dos Enemies, com relação a como se movimentam, se são iluminados ou não, entre outros, além da própria implementação da lógica por trás disso. Também foi difícil utilizar o polimorfismo nas células de forma efetiva, usando o método `activate()`, principalmente devido a diferentes células precisarem de acesso a diferentes objetos e informações para seus próprios métodos `activate()`, principalmente `Bonfire` e `Exit`.


# Destaques de Código

# Destaques de Pattern

# Conclusões e Trabalhos Futuros

# Diagramas

## Diagrama Geral do Projeto
![Diagrama Geral do Projeto]()

## Diagrama Geral de Componentes
![Diagrama Geral de Componentes]()



## Componente `GameBuilder`
Componente responsável por, para cada fase, gerar um objeto Map e Bravia a partir de um arquivo .csv descrevendo a fase.

![GameBuilder Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classe | map.GameBuilder
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interface | IMapGenerator

## Interfaces
Interfaces associadas a esse componente:

![GameBuilder Interfaces]()

## Detalhamento das Interfaces
### Interface `IGameCreator`
Interface que provê acesso aos serviços da Factory.
~~~
public interface IGameCreator {
	public Map getMap();
	public Bravia getBravia();
}
~~~

Método | Objetivo
-------| --------
`Map getMap()`  |  Retorna o objeto tipo Map associado ao jogo criado
`Bravia getBravia()` | Retorna o objeto tipo Bravia associado ao jogo criado

## Componente `CellFactory`
Componente responsável por criar objetos Cell da classe apropriada dependendo dos argumentos passados a ele.

![CellFactory Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classe | map.CellFactory
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interface | ICellFactory

## Interfaces
Interfaces associadas a esse componente:

![CellFactory Interfaces]()

## Detalhamento das Interfaces
### Interface `ICellFactory`

Interface que provê acesso ao método da Factory.
~~~
public interface ICellFactory {
	public Cell getCell(String objectID, Window window, Map mapGenerated, int iPos, int jPos);
}
~~~

Método | Objetivo
-------| --------
`Cell getCell(String objectID, Window window, Map mapGenerated, int iPos, int jPos)`  |  Retorna um objeto Cell cuja classe depende do parâmetro objectID

## Componente `EnemyFactory`
Componente responsável por criar objetos Enemy da classe apropriada dependendo dos argumentos passados a ele.

![EnemyFactory Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classe | map.EnemyFactory
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interface | IEnemyFactory

## Interfaces
Interfaces associadas a esse componente:

![EnemyFactory Interfaces]()

## Detalhamento das Interfaces
### Interface `IEnemyFactory`

Interface que provê acesso ao método da Factory.
~~~
public interface IEnemyFactory {
	public Enemy getEnemy(String objectID, Map mapGenerated, int iPos, int jPos);
}
~~~

Método | Objetivo
-------| --------
`public Enemy getEnemy(String objectID, Map mapGenerated, int iPos, int jPos)`  |  Retorna um objeto Enemy cuja classe depende do parâmetro objectID

## Componente `Map`
Componente responsável por representar o mapa do jogo.

![Map Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classe | map.Map
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interfaces | IMapProperties </br> IEnemyController </br> ILight

## Interfaces
Interfaces associadas a esse componente:

![Map Interfaces]()

Interface agregadora do componente em Java:
~~~
public interface IMap extends IEnemyController, IMapProperties, ILight {
}
~~~

## Detalhamento das Interfaces

### Interface `IEnemyController`
Interface responsável por controlar o movimento dos Enemies.
~~~
public interface IEnemyController {
  void moveEnemies();
}
~~~
Método | Objetivo
-------| --------
`void moveEnemies()` | Comunica ao Map para para os inimigos se moverem

### Interface `IMapProperties`
Interface que provê acesso às propriedades do mapa.
~~~
public interface IMapProperties {
	int getMapHeight();
	int getMapWidth();
	void setMapHeight(int mapHeight);
	void setMapWidth(int mapWidth);
	Cell getCell(int i, int j);
	Enemy getEnemy(int i, int j);
	Cell[][] getMapCell();
	Enemy[][] getMapEnemy();
	void setMapCell(Cell[][] mapCell);
	void setMapEnemy(Enemy[][] mapEnemy);
	Bravia getBravia();
	void setBravia(Bravia bravia);
	int getIBravia();
	int getJBravia();
	ArrayList<Enemy> listEnemies();
}
~~~

Método | Objetivo
-------| --------
`int getMapHeight()` | Retorna a altura do mapa (quantidade de linhas)
`int getMapWidth()` | Retorna a largura do mapa (quantidade de colunas)
`void setMapHeight(int mapHeight)` | Define a altura do mapa (quantidade de linhas)
`void setMapWidth(int mapWidth)` | Define a largura do mapa (quantidade de colunas)
`Cell getCell(int i, int j)` | Retorna a célula na posição de coordenadas (i, j)
`Enemy getEnemy(int i, int j)` | Retorna o inimigo que estiver nas coordenadas (i, j), null caso não tenha
`Cell[][] getMapCell()` | Retorna uma matriz de objetos do tipo Cell, representando a camada estática do mapa
`Enemy[][] getMapEnemy()` | Retorna uma matriz de objetos do tipo Enemy, representando a camada dinâmica do mapa
`void setMapCell(Cell[][] mapCell)` | Define a matriz de objetos tipo Cell associada a esse mapa
`void setMapEnemy(Enemy[][] mapEnemy)` | Define a matriz de objetos tipo Enemy associada a esse mapa
`Bravia getBravia()` | Retorna o objeto Bravia associado a esse mapa
`void setBravia(Bravia bravia)` | Define o objeto Bravia associado a esse mapa esse mapa
`int getIBravia()` | Retorna a coordenada i atual de Bravia
`int getJBravia()` | Retorna a coordenada j atual de Bravia
`ArrayList<Enemy> listEnemies())` | Retorna um ArrayList com todos os Enemies atualmente no mapa

### Interface `ILight`
Interface que provê acesso às propriedades de iluminação do mapa.
~~~
public interface ILight {
	void illuminate(int range, int iSource, int jSource);
	void illuminatePermanently(int range, int iSource, int jSource);
	void clearLights();
}
~~~

Método | Objetivo
-------| --------
`void illuminate(int range, int iSource, int jSource)` | Ilumina as células e Enemies em um raio "range" ao redor da posição (iSource, jSource)
`void illuminatePermanently(int range, int iSource, int jSource)` | Ilumina permanentemente as células em um raio "range" ao redor da posição (iSource, jSource)
`void clearLights()` | Desilumina todas as células que não estão permanentemente iluminadas

## Componente `Bravia`
Componente responsável por representar a personagem principal do jogo, Bravia.

![Bravia Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classe | bravia.Bravia
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interfaces | IBraviaProperties </br> IController </br> IPocket

## Interfaces
Interfaces associadas a esse componente:

![Bravia Interfaces]()

Interface agregadora do componente em Java:
~~~
public interface IBravia extends IController, IPocket, IBraviaProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IController`
Interface responsável por controlar o movimento de Bravia.
~~~
public interface IController {
  void move(char direction);
}
~~~

Método | Objetivo
-------| --------
`void move(char direction)` | Comunica a Bravia em qual direção ela deve andar, Up ''U'', Down "D", Right "R" ou Left "L".

### Interface `IPocket`
Interface que provê acesso ao inventário de Bravia.
~~~
public interface IPocket {
	  void addKey(Color color);
	  boolean hasKey(Color color);
	  void setKeyInventory(boolean[] keyInventory);
}
~~~

Método | Objetivo
-------| --------
`void addKey(Color color)` | Adiciona a chave da cor especificada ao inventário de Bravia
`boolean hasKey(Color color)` | Retorna `true` caso Bravia possua a chave da cor especificada, `false` caso contrário
`void setKeyInventory(boolean[] keyInventory)` | Redefine o inventário de Bravia de acordo com o vetor passado como argumento

### Interface `IBraviaProperties`
Interface que provê acesso a propriedades gerais de Bravia.
~~~
public interface IBraviaProperties {
	  int getIPos();
	  int getJPos();
	  void setIPos(int iPos);
	  void setJPos(int jPos);
	  int getRange();
	  Image getImage();
}
~~~

Método | Objetivo
-------| --------
`int getIpos()` | Retorna a coordenada i atual de Bravia
`int getJpos()` | Retorna a coordenada j atual de Bravia
`void setIPos(int iPos)` | Define a coordenada i de Bravia
`void setJPos(int jPos)` | Define a coordenada j de Bravia
`int getRange()` | Retorna o raio da tocha de Bravia
`Image getImage()` | Retorna o objeto Image associado a Bravia (para a interface gráfica)



## Componente `Enemy`
Componente responsável por representar os inimigos no jogo.
![Enemy Component]()

### Ficha Técnica
Campo | Valor
----- | -----
Classes | enemy.Enemy </br> enemy.EnemyGuardian </br> enemy.EnemyHunter
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Interfaces | IMovement </br> IEnemyProperties </br> Cloneable

## Interfaces
Interfaces associadas a esse componente:
![Enemy Interfaces]()


Interface agregadora do componente em Java:
~~~
public interface IBravia extends IController, IPocket, IBraviaProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `IMovement`
Interface responsável por controlar o movimento do Enemy.
~~~
public interface IMovement {
	char getMoveDirection();
}
~~~

Método | Objetivo
-------| --------
`char getMoveDirection()` | Retona um char que depende da direção na qual o Enemy irá fazer seu próximo movimento: Up ''U'', Down "D", Right "R" ou Left "L".

### Interface `IEnemyProperties`
Interface que provê acesso a propriedades gerais do Enemy.
~~~
public interface IEnemyProperties {
	boolean isLit();
	void setLit(boolean lit);
	void setIPos(int iPos);
	void setJPos(int jPos);
	void setImage(Image image);
	int getIPos();
	int getJPos();
	Image getImage();
}
~~~

Método | Objetivo
-------| --------
`boolean isLit()` | Retorna se o Enemy está ou não iluminado
`void setLit(boolean lit)` | Define o estado do Enemy como iluminado, se o parâmetro for true, ou como não iluminado caso contrário
`void setIPos(int iPos)` | Define a coordenada i do Enemy
`void setJPos(int jPos)` | Define a coordenada j do Enemy
`void setImage(Image image)` | Define o objeto Image associado ao Enemy
`int getIpos()` | Retorna a coordenada i atual do Enemy
`int getJpos()` | Retorna a coordenada j atual do Enemy
`Image getImage()` | Retorna o objeto Image associado ao Enemy (para a interface gráfica)

# Componente `Window`
![Window Component](docs/WindowComponent.PNG)

## Classe
Classe associada a esse componente:

![Window Class](docs/Classe_Window.PNG)

Campo | Valor
----- | -----
Classe | main.Window
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Objetivo | Representar a janela do jogo
Interface | -



# Componentes `Cell`
![Cell Component](docs/Cell_Component.PNG)

## Interfaces
Interfaces associadas a esse componente:

![Cell Interfaces](docs/diagrama_interface_celulas.png)

Campo | Valor
----- | -----
Classes | cells.Cell </br> cells.Chest </br> cells.Gate </br> cells.Wall </br> cells.Bonfire </br> cells.Key </br> cells.Exit </br>
Autores | Antonio Gabriel da Silva Fernandes </br> Lucas de Paula Soares
Objetivo | Representar as células no grid
Interface | ICellProperties

~~~
public interface ICellProperties {
  boolean isWalkable();
  boolean isPermanentlyLit();
  boolean isLit();
  setWalkable(boolean walkable);
  void setPermanentlyLit(boolean permanentlyLit);
  void setLit(boolean lit);
  String getCellType();
  void activate();
}
~~~

## Detalhamento das Interfaces

### Interface `ICellProperties`
Interface que provê as propriedades de uma célula.

Método | Objetivo
-------| --------
`boolean isWalkable()` | Retorna true caso seja possível andar por cima da célula, false caso contrário
`boolean isPermanentlyLit()` | Retorna true se a célula tem iluminação permanente, false caso contrário
`boolean isLit()` | Retorna se a célula está ou não iluminada
`setWalkable(boolean walkable)` | Define que é possível andar sobre a célula, se o parâmetro for true, ou que não é possível caso contrário
`void setPermanentlyLit(boolean permanentlyLit)` | Define que a célula tem iluminação permanente, se o parâmetro for true, ou que não tem caso contrário
`void setLit(boolean lit)` | Define o estado da célula como iluminado, se o parâmetro for true, ou como não iluminada caso contrário
`String getCellType()` | Retorna uma string baseado no seu tipo e seu estado atual
`void activate()` | Ativa a célula, fazendo com que ela mude seu estado com base no seu tipo (uma fogueira se torna uma fogueira acesa, por exemplo)

### Strings associadas a cada tipo de célula:
Tipo de célula | String
----- | -----
Nada | `--`
Wall | `Wa`
Gate (color X) | `GX`
Bonfire | `Bf`
Key (color X) | `KX`
Chest | `Ch`
Exit | `Ex`
Enemy | `MX`


# Plano de Exceções
## Diagrama da hierarquia de exceções
## Descrição das classes de exceção
