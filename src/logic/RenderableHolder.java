package logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public RenderableHolder() {
		entities = new CopyOnWriteArrayList<IRenderable>();
	}
	
	public void add(IRenderable i) {
		entities.add(i);
		Collections.sort(entities , new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable e1, IRenderable e2) {
				return -Integer.compare(e1.getZ(), e2.getZ());
			}
		});
	}

	public List<IRenderable> getRenderableList() {
		return entities;
	}
}
