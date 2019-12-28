package net.thebitspud.defender.assets;

import java.awt.image.BufferedImage;

import net.thebitspud.defender.assets.gfx.SpriteSheet;

//preparing game assets beforehand to improve usability

public class AssetLoader {
	private ResourceLoader rl;

	private BufferedImage tile[][], gameIcon[][], button[][], mob[][],
	flame[], explosion[], explosion2[], spaceBD[], forestBD[], player;
	private SpriteSheet entitySheet, iconSheet, assetSheet, spaceBDSheet, forestBDSheet;

	private final int gridSize = 16;

	private int activeTrack;

	public AssetLoader(ResourceLoader rl) {
		this.rl = rl;

		loadImages();
		rl.initTracks();

		activeTrack = 0;
		sound = true;
		music = true;

		playMusic();
	}

	public void loadImages() {
		//game icons

		iconSheet = new SpriteSheet(rl.loadImage("gameicon"));
		gameIcon = new BufferedImage[8][8];

		for(int x = 0; x < gameIcon.length; x++)
			for(int y = 0; y < gameIcon[0].length; y++)
				gameIcon[x][y] = iconSheet.crop(x * gridSize, y * gridSize, gridSize, gridSize);

		//spaghet

		button = new BufferedImage[2][3];

		for(int x = 0; x < button.length; x++)
			for(int y = 0; y < button[0].length; y++)
				button[x][y] = gameIcon[x][y];

		//entities

		entitySheet = new SpriteSheet(rl.loadImage("entity"));

		mob = new BufferedImage[3][4];
		mob[0][0] = entitySheet.crop(gridSize, 0, gridSize / 2, gridSize / 2); //Probe
		mob[0][1] = entitySheet.crop(gridSize, gridSize / 2, gridSize / 2, gridSize / 2); //Drone
		mob[0][2] = entitySheet.crop((int) (gridSize * 1.5), 0, gridSize / 2, gridSize / 2); //Mine
		mob[0][3] = entitySheet.crop((int) (gridSize * 1.5), gridSize / 2, gridSize / 2, gridSize / 2); //Drifter
		mob[1][0] = entitySheet.crop(gridSize * 2, 0, gridSize, gridSize); //Saucer
		mob[1][1] = entitySheet.crop(gridSize * 3, 0, gridSize, gridSize); //SaucerV2
		mob[1][2] = entitySheet.crop(gridSize * 4, 0, gridSize, gridSize); //Shield
		mob[1][3] = entitySheet.crop(gridSize * 5, 0, gridSize, gridSize); //Vaporizer
		mob[2][0] = entitySheet.crop(gridSize * 6, 0, gridSize, gridSize); //Supernova
		mob[2][1] = entitySheet.crop(gridSize * 7, 0, gridSize, gridSize); //Obstructor

		player = entitySheet.crop(0, 0, gridSize, gridSize);

		//visual assets

		assetSheet = new SpriteSheet(rl.loadImage("asset"));
		flame = new BufferedImage[8];

		for(int i = 0; i < flame.length; i++) flame[i] = assetSheet.crop(i * 8, 0, 8, 16);

		explosion = new BufferedImage[8];

		for(int i = 0; i < explosion.length; i++) explosion[i] = assetSheet.crop(i * 16, 16, 16, 16);

		//more hardcoded spaghet

		explosion2 = new BufferedImage[12];

		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 4; y++)
				explosion2[(3 * x) + y] = assetSheet.crop(y * 32, 32 + (x * 32), 32, 32);

		//backdrops

		spaceBDSheet = new SpriteSheet(rl.loadImage("backdrops/space"));
		spaceBD = new BufferedImage[4];

		for(int i = 0; i < spaceBD.length; i++) spaceBD[i] = spaceBDSheet.crop(i * 800, 0, 800, 416);

		forestBDSheet = new SpriteSheet(rl.loadImage("backdrops/forest"));
		forestBD = new BufferedImage[4];

		for(int i = 0; i < forestBD.length; i++) forestBD[i] = forestBDSheet.crop(i * 800, 0, 800, 416);
	}

	private boolean music, sound;

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}

	public void playSound(String path) {
		if(sound) rl.getAudio("sfx/" + path, false);
	}

	public void setMusicTrack(int index) {
		activeTrack = index;

		playMusic();
	}

	public void cycleMusicTrack() {
		if(activeTrack < rl.getTracks().length - 1) activeTrack++;
		else activeTrack = 0;

		playMusic();
	}

	public void playMusic() {
		stopMusic();
		if(music) rl.playTrack(activeTrack);
	}

	public void stopMusic() {
		rl.resetAudio();
	}

	public int getActiveTrack() {
		return activeTrack;
	}

	public BufferedImage getTile(int x, int y) {
		return tile[x][y];
	}

	public BufferedImage getIcon(int x, int y) {
		return gameIcon[x][y];
	}

	public BufferedImage[] getButton(int i) {
		return button[i];
	}

	public BufferedImage getPlayer() {
		return player;
	}

	public BufferedImage getFlame(int i) {
		return flame[i];
	}

	public BufferedImage getMob(int x, int y) {
		return mob[x][y];
	}

	public BufferedImage getExplosion(int type, int i) {
		if(type == 2) return explosion2[i];
		else return explosion[i];
	}

	public BufferedImage[] getSpaceBD() {
		return spaceBD;
	}

	public BufferedImage[] getForestBD() {
		return forestBD;
	}

	public int getGridSize() {
		return gridSize;
	}
}
