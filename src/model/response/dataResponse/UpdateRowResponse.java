package model.response.dataResponse;

import java.util.ArrayList;

import model.TableCellModel;
import model.response.ServerResponse;

public class UpdateRowResponse extends ServerResponse{
	private ArrayList<TableCellModel> tableCells;

	public ArrayList<TableCellModel> getTableCells() {
		return tableCells;
	}

	public void setTableCells(ArrayList<TableCellModel> tableCells) {
		this.tableCells = tableCells;
	}
	
}
