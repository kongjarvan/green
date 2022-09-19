package site.metacoding.red.domain.loves;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Loves {
	private Integer id;
	private Integer usersId;
	private Integer boardsId;
	private Timestamp createdAt; // At 시분초 다 표현할때, Dt 년원일
	
	
	public Loves(Integer usersId, Integer boardsId) {
		this.usersId = usersId;
		this.boardsId = boardsId;
	}
	
	
	

}
