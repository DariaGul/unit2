package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import task1.Stationery;

// бумажные изделия
public abstract class PaperProduct extends Stationery {

    protected List<Record> records;
    protected int durability;

    protected int getDurability() {
        return durability;
    }

    protected void setDurability(int durability) {
        this.durability = durability;
    }

    public PaperProduct() {
        records = new ArrayList<>();
    }

    protected UUID saveRecord(String text, WritingUtensil writingTool) {
        Record newRecord = null;
        if (durability != 0) {
            newRecord = new Record(text, writingTool);
            records.add(newRecord);
            durability--;
        }
        return newRecord.getId();
    }

    protected void deleteRecord(UUID recordId, Corrector corrector) {
        WritingUtensil writingTool = getWritingTool(recordId);
        if (writingTool != null && (corrector.getErasingEverything() || !writingTool.getIndelible())) {
            records
                .stream()
                .filter(c -> c.getId().equals(recordId))
                .findAny()
                .ifPresent(value -> {
                    records.remove(value);
                    durability++;
                });
        }
    }

    protected WritingUtensil getWritingTool(UUID recordId) {
        Optional<Record> record = records
            .stream()
            .filter(c -> c.getId().equals(recordId))
            .findAny();
        return record.map(Record::getWritingTool).orElse(null);
    }

    public List<Record> getRecords() {
        return records;
    }
}
