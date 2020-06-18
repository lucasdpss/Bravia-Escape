package map;

public interface ILight {
	void illuminate(int range, int iSource, int jSource);
	void illuminatePermanently(int range, int iSource, int jSource);
	void clearLights();
}
