import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;



class MenuApp {
    private static MenuApp instance;

    private MenuApp() {
        createGUI();
    }

    public static MenuApp getInstance() {
        if (instance == null) {
            instance = new MenuApp();
        }
        return instance;
    }

    private void createGUI() {
        JFrame frame = new JFrame("Menu Application");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setLayout(new GridLayout(5, 1));

        JButton TurkButton = createCuisineButton("Türk Mutfağı",
                Arrays.asList("Mercimek çorbası", "Domates Çorbası", "Ezogelin Çorbası"),
                Arrays.asList("Tas Kebabı", "Bezelye", "İmam Bayıldı"),
                Arrays.asList("İrmik Helvası", "Sütlaç", "Baklava"),
                Arrays.asList("Paçanga Böreği", "Mücver", "Mercimek Köftesi"),
                Arrays.asList("Haydari", "Babagannuş", "Humus"));


        JButton FransızButton = createCuisineButton("Fransız mutfağı",
                Arrays.asList("Soğan Çorbası", "Potage aux Legumes", "Potage Parmentier"),
                Arrays.asList("Croque Madame", "Brioche", "Ratatouille"),
                Arrays.asList("Rokoko", "Sufle", "Mousse"),
                Arrays.asList("Mentaiko", "Kruvasan", "Coquilles Saint-Jacques"),
                Arrays.asList("Pesto", "Fırında Elma Brie", "Cornichon Turşu"));

        JButton ItalyanButton = createCuisineButton("İtalyan mutfağı",
                Arrays.asList("Ribollita Çorbası", "Minestrone Çorbası", "Stracciatella Çorbası"),
                Arrays.asList("Risotto", "Pizza", "Lazanya"),
                Arrays.asList("Tiramisu", "Panna Cotta", "Cannoli"),
                Arrays.asList("Arabiata Soslu Makarna", "Bruschetta", "Somon Carpaccio"),
                Arrays.asList("Giardiniera ", "Klasik Sicilya Arancini", "Ligurya Nohut Gözleme"));

        JButton IspanyolButton = createCuisineButton("İspanyol mutfağı",
                Arrays.asList("Gazpacho Çorbası", "Balık Yahni", "Sopa de ajo"),
                Arrays.asList("Fajita", "Paella", "Patatas Bravas"),
                Arrays.asList("Bizcocho", "Churros", "San Sebastian Cheesecake"),
                Arrays.asList("Jamon Kroket", "İspanyol Omleti", "Empanadas"),
                Arrays.asList("Mantarlı Tapas", "Pan con tomate", "Sarımsaklı karides"));

        panel.add(TurkButton);
        panel.add(FransızButton);
        panel.add(ItalyanButton);
        panel.add(IspanyolButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Tüm verileri sıfırlamak istediğinize emin misiniz?",
                    "Verileri Sıfırla", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                resetData();
                JOptionPane.showMessageDialog(null, "Veriler sıfırlandı!");
            }
        });
        panel.add(resetButton);

    }

    private void resetData() {
        File[] files = new File(".").listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private JButton createCuisineButton(String name, List<String> soupNames, List<String> mainDishNames,
                                        List<String> dessertNames, List<String> warmAppetizerNames, List<String> mezeNames) {
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            CuisineFrame cuisineFrame = new CuisineFrame.CuisineFrameBuilder()
                    .name(name)
                    .soupNames(soupNames)
                    .mainDishNames(mainDishNames)
                    .dessertNames(dessertNames)
                    .warmAppetizerNames(warmAppetizerNames)
                    .mezeNames(mezeNames)
                    .build();
            cuisineFrame.display();
        });
        return button;
    }


    private static class CuisineFrame {
        private final String name;
        private final List<String> soupNames;
        private final List<String> mainDishNames;
        private final List<String> dessertNames;
        private final List<String> warmAppetizerNames;
        private final List<String> mezeNames;

        private CuisineFrame(String name, List<String> soupNames, List<String> mainDishNames,
                             List<String> dessertNames, List<String> warmAppetizerNames, List<String> mezeNames) {
            this.name = name;
            this.soupNames = soupNames;
            this.mainDishNames = mainDishNames;
            this.dessertNames = dessertNames;
            this.warmAppetizerNames = warmAppetizerNames;
            this.mezeNames = mezeNames;
        }

        public void display() {
            JFrame frame = new JFrame(name);
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(400, 400));
            panel.setLayout(new GridLayout(5, 1));

            JButton soupButton = createMealButton("Çorba", soupNames);
            JButton mainDishButton = createMealButton("Ana Yemek", mainDishNames);
            JButton dessertButton = createMealButton("Tatlı", dessertNames);
            JButton warmAppetizerButton = createMealButton("Ara Sıcak", warmAppetizerNames);
            JButton mezeButton = createMealButton("Meze", mezeNames);

            panel.add(soupButton);
            panel.add(mainDishButton);
            panel.add(dessertButton);
            panel.add(warmAppetizerButton);
            panel.add(mezeButton);

            frame.add(panel, BorderLayout.CENTER);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }

        private JButton createMealButton(String mealName, List<String> names) {
            JButton button = new JButton(mealName);
            button.addActionListener(e -> {
                MealFrame mealFrame = new MealFrame.MealFrameBuilder()
                        .mealName(mealName)
                        .names(names)
                        .build();
                mealFrame.display();
            });
            return button;
        }


        private static class CuisineFrameBuilder {
            private String name;
            private List<String> soupNames;
            private List<String> mainDishNames;
            private List<String> dessertNames;
            private List<String> warmAppetizerNames;
            private List<String> mezeNames;

            public CuisineFrameBuilder name(String name) {
                this.name = name;
                return this;
            }

            public CuisineFrameBuilder soupNames(List<String> soupNames) {
                this.soupNames = soupNames;
                return this;
            }

            public CuisineFrameBuilder mainDishNames(List<String> mainDishNames) {
                this.mainDishNames = mainDishNames;
                return this;
            }

            public CuisineFrameBuilder dessertNames(List<String> dessertNames) {
                this.dessertNames = dessertNames;
                return this;
            }

            public CuisineFrameBuilder warmAppetizerNames(List<String> warmAppetizerNames) {
                this.warmAppetizerNames = warmAppetizerNames;
                return this;
            }

            public CuisineFrameBuilder mezeNames(List<String> mezeNames) {
                this.mezeNames = mezeNames;
                return this;
            }

            public CuisineFrame build() {
                return new CuisineFrame(name, soupNames, mainDishNames, dessertNames, warmAppetizerNames, mezeNames);
            }
        }
    }


    private static class MealFrame {
        private final String mealName;
        private final List<String> names;

        private MealFrame(String mealName, List<String> names) {
            this.mealName = mealName;
            this.names = names;
        }

        public void display() {
            JFrame frame = new JFrame(mealName);
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(400, 400));
            panel.setLayout(new GridLayout(names.size(), 1));

            for (String name : names) {
                panel.add(createDishButton(name));
            }

            frame.add(panel, BorderLayout.CENTER);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }

        private JButton createDishButton(String name) {
            JButton button = new JButton(name);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);

                    File file = new File(name + ".txt");
                    if (file.exists()) {
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String line = reader.readLine();
                            while (line != null) {
                                textArea.append(line + "\n");
                                line = reader.readLine();
                            }
                            reader.close();
                        } catch (IOException exception) {
                            textArea.setText("Tarif bulunamadı.");
                        }
                    } else {
                        textArea.setText("Tarif yok. Kendi tarifinizi ekleyin!");
                    }

                    JPanel panel = new JPanel();
                    panel.add(new JScrollPane(textArea));

                    if (!file.exists()) {
                        int result = JOptionPane.showConfirmDialog(null, "Tarif yok. Kendi tarifinizi eklemek ister misiniz?",
                                "Tarif Yok", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            JTextArea recipeTextArea = new JTextArea(10, 30);
                            JScrollPane recipeScrollPane = new JScrollPane(recipeTextArea);
                            int recipeResult = JOptionPane.showConfirmDialog(null, recipeScrollPane,
                                    "Tarifi girin", JOptionPane.OK_CANCEL_OPTION);
                            if (recipeResult == JOptionPane.OK_OPTION) {
                                String tarif = recipeTextArea.getText();
                                if (!tarif.isEmpty()) {
                                    try {
                                        FileWriter writer = new FileWriter(file);
                                        writer.write(tarif);
                                        writer.close();
                                        JOptionPane.showMessageDialog(null, "Tarif başarıyla eklendi! Afiyet Olsun.");
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "Tarif eklenirken bir hata oluştu.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Geçerli bir tarif girmediniz.");
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, panel, name, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            return button;
        }



        private static class MealFrameBuilder {
            private String mealName;
            private List<String> names;

            public MealFrameBuilder mealName(String mealName) {
                this.mealName = mealName;
                return this;
            }

            public MealFrameBuilder names(List<String> names) {
                this.names = names;
                return this;
            }

            public MealFrame build() {
                return new MealFrame(mealName, names);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MenuApp app = MenuApp.getInstance();
    }
}