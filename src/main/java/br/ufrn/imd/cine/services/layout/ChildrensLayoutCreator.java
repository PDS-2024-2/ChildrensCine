package br.ufrn.imd.cine.services.layout;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufrn.imd.cine.model.layout.ChildrensLayout;
import br.ufrn.imd.cine.model.seat.ChildrensUnit;
import br.ufrn.imd.cine.model.seat.ChildrensUnitType;
import br.ufrn.imd.cine.model.seat.SeatRecord;
import br.ufrn.imd.cine.repositories.ChildrensLayoutRepository;
import br.ufrn.imd.cine.repositories.ChildrensUnitRepository;
import br.ufrn.imd.cineframework.models.layout.AbstractLayout;
import br.ufrn.imd.cineframework.services.layout.LayoutCreator;
import jakarta.validation.constraints.NotNull;

@Component
public class ChildrensLayoutCreator extends LayoutCreator {

	@Autowired
	private ChildrensUnitRepository seatRepository;

	@Autowired
	private ChildrensLayoutRepository layoutRepository;

	@Override
	public AbstractLayout createLayout(Object data) {

		SeatRecord[][] seatingLayout = (SeatRecord[][]) data;

		ChildrensLayout simpleLayout = new ChildrensLayout();

		simpleLayout.setSeats(extractSeats(seatingLayout));
		simpleLayout.onCreate();

		ChildrensLayout savedLayout = layoutRepository.save(simpleLayout);

		return savedLayout;
	}

	private List<ChildrensUnit> extractSeats(@NotNull SeatRecord[][] seatingLayout) {

		List<ChildrensUnit> seats = new ArrayList<>();

		for (int rowi = 1; rowi <= seatingLayout.length; rowi++) {
			SeatRecord[] seatRow = seatingLayout[rowi - 1];

			for (int columnj = 1; columnj <= seatRow.length; columnj++) {
				ChildrensUnitType st = seatRow[columnj - 1].seatType();

				if (st == null)
					continue;

				ChildrensUnit s = new ChildrensUnit();
				s.setChildrensUnitType(st);

				String code = numberToLetters(rowi) + columnj;

				s.setCode(code);
				s.onCreate();

				ChildrensUnit savedSeat = seatRepository.save(s);
				seats.add(savedSeat);
			}
		}

		return seats;
	}

	private static String numberToLetters(int n) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			n--;
			char letter = (char) ('A' + (n % 26));
			sb.append(letter);
			n /= 26;
		}
		return sb.reverse().toString();
	}

}
