import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;


public class ReportParserTest {

	@Test
	public void testCreateReport() throws Exception{
		String jsonStr = "{" +
			"\"Incidents\": [" +
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"2\", \"DateTimeReported\":\"2013-02-13 15:15:15\" }," +
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"1\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Flood\" , \"LevelReported\":\"1\", \"DateTimeReported\":\"2013-02-35 15:08:19\"  },  " + 
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"3\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }, " +
			 "{ \"TypeName\":\"Flood\" , \"LevelReported\":\"3\", \"DateTimeReported\":\"2013-05-12 05:18:21\"  }," +
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"1\", \"DateTimeReported\":\"2013-02-13 15:15:15\" }," +
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"1\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Flood\" , \"LevelReported\":\"2\", \"DateTimeReported\":\"2013-05-35 23:22:19\"  }," +
			 "{ \"TypeName\":\"Smoke\" , \"LevelReported\":\"3\", \"DateTimeReported\":\"2013-02-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Flood\" , \"LevelReported\":\"1\", \"DateTimeReported\":\"2013-01-35 11:08:19\"  },   " +
			 "{ \"TypeName\":\"Flood\" , \"LevelReported\":\"3\", \"DateTimeReported\":\"2013-06-30 11:21:23\"  },   " + 
			 "{ \"TypeName\":\"Fire\" , \"LevelReported\":\"2\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }" +
			"]," +
			"\"Haze\": [" +
			 "{ \"TypeName\":\"Emergency\" , \"PSI\":\"20\", \"DateTimeReported\":\"2013-02-13 15:15:15\" }," +
			 "{ \"TypeName\":\"Emergency\" , \"PSI\":\"200\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"33\", \"DateTimeReported\":\"2013-02-35 15:08:19\"  },   " +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"179\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }, " +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"235\", \"DateTimeReported\":\"2013-05-12 05:18:21\"  }," +
			 "{ \"TypeName\":\"Emergency\" , \"PSI\":\"123\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"229\", \"DateTimeReported\":\"2013-02-35 15:08:19\"  },   " +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"30\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }," +
			 "{ \"TypeName\":\"Emergency\" , \"PSI\":\"99\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"105\", \"DateTimeReported\":\"2013-02-35 15:08:19\"  },   " +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"237\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }," +
			 "{ \"TypeName\":\"Emergency\" , \"PSI\":\"301\", \"DateTimeReported\":\"2013-03-13 16:15:18\"  }," +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"10\", \"DateTimeReported\":\"2013-02-35 15:08:19\"  },   " +
			 "{ \"TypeName\":\"Mild\" , \"PSI\":\"39\", \"DateTimeReported\":\"2013-07-30 17:21:23\"  }" +
			"]" +
			"}";
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonStr);
		assertEquals("Result should be 1", 1, ReportParser.createReport(jsonObject));
	}

}
