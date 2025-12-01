import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
/*
 * This class is the overall main window for the game. 
 * It contains one Region as the background, one ActorMovementPane for the movable characters, and a UIPane for the healthbar and Dialoguebox
 */
public class GameWindowScene extends Scene {
	private Player player;
	private ActorMovementPane actorPane;
	private ArrayList<Actor> actors;
	private UIPane uiPane;
	
	private boolean uiActive;
	
	private StackPane root;
	
	public GameWindowScene(int width, int height) {
		super(new StackPane(), width, height);
		
		
		
		root = (StackPane) super.getRoot();
		player = new Player(100, 125, "Placeholder");
		
		Region region = new Region(500, 500, player);
		root.getChildren().add(region);
		
		actors = new ArrayList<Actor>();
		NonPlayerCharacter npc = new NonPlayerCharacter(100, 100);
		actors.add(npc);
		npc.setxPos(1100);
		npc.setyPos(1000);
		
		EnemyCharacter enemy = new EnemyCharacter(50, 100);
		actors.add(enemy);
		enemy.setxPos(900);
		enemy.setyPos(900);
		
		actorPane = new ActorMovementPane(500, 500, player, actors);
		root.getChildren().add(actorPane);
		
		
		
		uiPane = new UIPane(500, 500, player);
		uiActive = false;
		root.getChildren().add(uiPane);
		
		actorPane.setUIPane(uiPane);
		actorPane.setRegion(region);
		
		SetUpControls();
		root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			region.resizeScreen(this.getWidth(), this.getHeight());
			actorPane.resizeScreen(this.getWidth(), this.getHeight());
			uiPane.resizeScreen(this.getWidth(), this.getHeight());
		});
	}
	
	private void SetUpControls() {
		setOnMouseClicked(event -> {
			
			for (Actor actor : actors) {
				if (actor instanceof NonPlayerCharacter) {
					NonPlayerCharacter character = (NonPlayerCharacter) actor; 
					if (character.getBoundsInParent().intersects(event.getX(), event.getY(), 1, 1)) {
						if (uiActive) {
							// Do not open a dialogueBox if the UI is already active
						}
						else if (Actor.GetDistance(player, character) < 100) {
							uiPane.OpenDialogue(character.GetDialogue(0));
							ActivateUI();
						}
						else {
							uiPane.OpenDialogue(character.GetDialogue(1));
							ActivateUI();
						}
						break;
					}
				}
				else if (actor instanceof EnemyCharacter) {
					EnemyCharacter enemy = (EnemyCharacter) actor; 
					if (enemy.getBoundsInParent().intersects(event.getX(), event.getY(), 1, 1)) {
						if (Actor.GetDistance(player, enemy) < player.getRange()) {
							if (enemy.TakeDamage(player.getDamage())) { 
								actorPane.Kill(enemy);
								break;
							}
						}
					}
				}
				
			}
			
			
			if (event.getY() >= this.getHeight() - 100) {
				uiPane.CloseDialogue();
				uiActive = false;
			}
		});
		
		setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.SHIFT) {
				if (uiActive) {
					if (uiPane.inventoryOpen()) {
						uiPane.closeInventory();
						uiActive = false;
					}
				}
				else {
					// : Open the inventory
					uiPane.openInventory(player);
					ActivateUI();
				}
			}
			
			if (event.getCode() == KeyCode.SPACE) {
				if (uiActive) {
					if (uiPane.inventoryOpen()) {
						// Don't close the dialogue box because the active element is the inventory
					}
					else {
						uiPane.CloseDialogue();
						uiActive = false;
					}
				}
				else {
					for (Actor actor : actors) {
						if (actor instanceof NonPlayerCharacter) {
							NonPlayerCharacter character = (NonPlayerCharacter) actor;
							if (Actor.GetDistance(player, character) < 100) {
								uiPane.OpenDialogue(character.GetDialogue(0));
								ActivateUI();
								break;
							}
						}
						else if (actor instanceof EnemyCharacter) {
							EnemyCharacter enemy = (EnemyCharacter) actor; 
							if (Actor.GetDistance(player, enemy) < player.getRange()) {
								if (enemy.TakeDamage(player.getDamage())) { 
									actorPane.Kill(enemy);
									break;
								}
							}
						}
					}
				}
			}
			
			if (uiActive) {
				// Prevents movement if the UI is active
			}
			else if (event.getCode() == KeyCode.D) {
				actorPane.setXMovement(player.PLAYER_SPEED);
			}
			else if (event.getCode() == KeyCode.A) {
				actorPane.setXMovement(-player.PLAYER_SPEED);
			}
			else if (event.getCode() == KeyCode.W) {
				actorPane.setYMovement(-player.PLAYER_SPEED);
			}
			else if (event.getCode() == KeyCode.S) {
				actorPane.setYMovement(player.PLAYER_SPEED);
			}
		});
		
		setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.D) {
				actorPane.setXMovement(0);
			}
			else if (event.getCode() == KeyCode.A) {
				actorPane.setXMovement(0);
			}
			else if (event.getCode() == KeyCode.W) {
				actorPane.setYMovement(0);
			}
			else if (event.getCode() == KeyCode.S) {
				actorPane.setYMovement(0);
			}
		});
	}
	
	private void ActivateUI() {
		uiActive = true;
		actorPane.setXMovement(0);
		actorPane.setYMovement(0);
	}
}
