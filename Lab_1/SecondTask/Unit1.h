//---------------------------------------------------------------------------

#ifndef Unit1H
#define Unit1H
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include "sButton.hpp"
#include "sEdit.hpp"
#include "sLabel.hpp"
#include <ExtCtrls.hpp>
//---------------------------------------------------------------------------
class TTimerForm : public TForm
{
__published:	// IDE-managed Components
        TsEdit *timeEditor;
        TsEdit *stopwatchEditor;
        TsButton *startButton;
        TsButton *stopButton;
        TsButton *zeroButton;
        TsLabel *taskLabel;
        TsLabel *stopwatchLabel;
        TsButton *exitButton;
        TTimer *Timer1;
        TTimer *Timer2;

        void __fastcall onExitButtonClick(TObject *Sender);
        void __fastcall onStartButtonClick(TObject *Sender);
        void __fastcall onStopButtonClick(TObject *Sender);
        void __fastcall onFirstTimerCall(TObject *Sender);
        void __fastcall onTimerTimerCall(TObject *Sender);
        void __fastcall onZeroButtonClick(TObject *Sender);
private:	// User declarations
public:		// User declarations
        __fastcall TTimerForm(TComponent* Owner);
};
//---------------------------------------------------------------------------
extern PACKAGE TTimerForm *TimerForm;
//---------------------------------------------------------------------------
#endif
