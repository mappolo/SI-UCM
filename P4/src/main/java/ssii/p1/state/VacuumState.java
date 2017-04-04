/*Custom Vacuum World Example 
Copyright (C) 2015  Jorge J. Gomez-Sanz

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/	

package ssii.p1.state;

import ssii.p1.actions.Location;
import aima.core.agent.EnvironmentState;
import aima.core.agent.State;
import aima.core.search.framework.Problem;


public class VacuumState implements EnvironmentState, State{
	int cells[][];	
	Location vacLoc=new Location(0,0);
	final static int WIDTH=10;
	final static int HEIGHT=10;

	public VacuumState(Location vacuumloc, int [][] world){
		this.vacLoc=vacuumloc;
		this.cells=world;
	}

	public VacuumState clone(){
		int ncells[][]=new int[cells.length][cells[0].length];
		for (int x=0;x<cells.length;x++)	
			for (int y=0;y<cells[0].length;y++) 
				ncells[x][y]=cells[x][y];
		return new VacuumState(vacLoc.clone(), ncells);

	}

	public int getGlobalDirtCount(){
		int dcount=0;
		for (int x=0;x<cells.length;x++)	
			for (int y=0;y<cells[0].length;y++)
				dcount=dcount+cells[x][y];
		return dcount;
	}


	public Location getLocation(){
		return vacLoc;
	}


	public int getDirt(){				
		return cells[vacLoc.getX()][vacLoc.getY()];
	}

	public void moveRight(){
		vacLoc.setX(Math.min(cells.length-1, vacLoc.getX()+1));
	}

	public void moveLeft(){
		vacLoc.setX(Math.max(0, vacLoc.getX()-1));
	}

	public void moveUp(){
		vacLoc.setY(Math.max(0, vacLoc.getY()-1));
	}

	public void moveDown(){
		vacLoc.setY(Math.min(cells[0].length-1,vacLoc.getY()+1));
	}
	
	public int getWidth(){
		return cells.length;
	}
	
	public int getHeight(){
		return cells[0].length;
	}

	public void suck(int aid){
		cells[vacLoc.getX()][vacLoc.getY()]=0;
	}
	public String toString(){
		String map="";
		for (int y=0;y<cells[0].length;y++){
			String row="";
			for (int x=0;x<cells.length;x++){

				if (x==vacLoc.getX() && y==vacLoc.getY())
					row=row+"[*|"+cells[x][y]+"]";
				else
					row=row+"[_|"+cells[x][y]+"]";
			}				

			map=map+row+"\n";
		}


		return map;
	}


	public static void main(String args[]){

	}

	public void addDirt() {
		int x=(int)(Math.random()*cells.length);
		int y=(int)(Math.random()*cells[0].length);
		cells[x][y]=5;
		
	}

	public int getDirtAt(int x, int y) {
		return cells[x][y];
	}

}
