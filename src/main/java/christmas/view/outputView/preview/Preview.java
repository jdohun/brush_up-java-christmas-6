package christmas.view.outputView.preview;

import christmas.dto.ChristmasPromotionPreview;

import static christmas.view.outputView.preview.PreviewMessage.*;

public final class Preview {
    private Preview() {
    }

    public static Preview getInstance() {
        return Holder.PREVIEW;
    }

    public void printPreview(ChristmasPromotionPreview christmasPromotionPreview) {

    }

    private class Holder {
        private static final Preview PREVIEW = new Preview();
    }
}
