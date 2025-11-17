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
		
		Region region = new Region(500, 500);
		root.getChildren().add(region);
		
		player = new Player(100, 125, "Placeholder");
		actors = new ArrayList<Actor>();
		NonPlayerCharacter npc = new NonPlayerCharacter(100, 100);
		actors.add(npc);
		npc.setLayoutX(300);
		npc.setxPos(100);
		npc.setLayoutY(200);
		npc.setyPos(0);
		
		EnemyCharacter enemy = new EnemyCharacter(50, 100);
		actors.add(enemy);
		enemy.setLayoutX(100);
		enemy.setxPos(-100);
		enemy.setLayoutY(200);
		enemy.setyPos(0);
		
		actorPane = new ActorMovementPane(500, 500, player, actors);
		root.getChildren().add(actorPane);
		
		
		
		uiPane = new UIPane(500, 500, player);
		uiActive = false;
		root.getChildren().add(uiPane);
		
		actorPane.setUIPane(uiPane);
		
		SetUpControls();
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
						else if (GetDistance(player.getxPos(), player.getyPos(), character.getxPos(), character.getyPos()) < 100) {
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
						if (GetDistance(player.getxPos(), player.getyPos(), enemy.getxPos(), enemy.getyPos()) < 100) {
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
							if (GetDistance(player.getxPos(), player.getyPos(), character.getxPos(), character.getyPos()) < 100) {
								uiPane.OpenDialogue(character.GetDialogue(0));
								ActivateUI();
								break;
							}
						}
						else if (actor instanceof EnemyCharacter) {
							EnemyCharacter enemy = (EnemyCharacter) actor; 
							if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
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
				actorPane.setXMovement(1);
			}
			else if (event.getCode() == KeyCode.A) {
				actorPane.setXMovement(-1);
			}
			else if (event.getCode() == KeyCode.W) {
				actorPane.setYMovement(-1);
			}
			else if (event.getCode() == KeyCode.S) {
				actorPane.setYMovement(1);
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
	
	private double GetDistance(double x1, double y1, double x2, double y2) {
		double distance = Math.hypot(x1 - x2, y1 - y2);
		return distance;
	}
}
