
create table questions(
	unique_id int,
    category varchar(30),
    question_id int, 
    question varchar(200),
    true_answer varchar(100),
    fake_answer_1 varchar(100),
    fake_answer_2 varchar(100),
    fake_answer_3 varchar(100)

);


INSERT INTO questions
VALUES	(1, "history", 1, "Wann begann der Zweite Weltkrieg?", "1939", "1914", "1945", "1950"),
		(2, "history", 2, "Wer war der erste Präsident der Vereinigten Staaten?", "George Washington", "Thomas Jefferson","Abraham Lincoln" , "John Adams"),
		(3, "history", 3, "Welches Jahr wird allgemein als das Jahr des Mauerfalls in Berlin betrachtet?" , "1989", "1985", "1987","1991"),
		(4, "history", 4, "Welche antike Zivilisation baute die Pyramiden von Gizeh?", "Ägypter", "Mesopotamier", "Griechen","Römer"),
		(5, "history", 5, "Wer schrieb die 95 Thesen, die die Reformation einleiteten?", " Martin Luther", " Johannes Calvin", "Ulrich Zwingli", "Philipp Melanchthon"),
		(6, "science", 1, "Wer entwickelte die allgemeine Relativitätstheorie?", "Albert Einstein", "Isaac Newton", "Niels Bohr", "Stephen Hawking"),
		(7, "science", 2, "Was ist die chemische Formel für Wasser?", "H₂O", "CO₂", "NaCl", "CH₄"),
		(8, "science", 3, "Wer gilt als der Vater der modernen Evolutionstheorie?", "Charles Darwin", "Gregor Mendel", "Alfred Russel Wallace", "Jean-Baptiste Lamarck"),
		(9, "science", 4, "Was ist das häufigste Element im Universum?", " Wasserstoff", "Helium", "Sauerstoff", "Kohlenstoff"),
		(10, "science", 5, "Welches Organ ist für das Pumpen von Blut im menschlichen Körper verantwortlich?", "Herz", "Lunge", "Leber", "Niere");

select * from questions;