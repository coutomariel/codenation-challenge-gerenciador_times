package br.com.codenation.desafio.comparators;

import java.util.Comparator;

import br.com.codenation.desafio.model.Time;

public class TimeIdComparator implements Comparator<Time> {

	@Override
	public int compare(Time time, Time outroTime) {
		if (time.getId() < outroTime.getId()) {
			return -1;
		}
		if (time.getId() > outroTime.getId()) {
			return 1;
		}
		return 0;
	}

}
